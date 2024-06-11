package com.wangweicheng.rbac.service.impl;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/11
 *@Time: 15:06
 */

import com.alibaba.fastjson.JSON;
import com.wangweicheng.rbac.common.Constants;
import com.wangweicheng.rbac.mapper.EmployeeMapper;
import com.wangweicheng.rbac.mapper.PermissionMapper;
import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.vo.LoginInfoVo;
import com.wangweicheng.rbac.service.ILoginService;
import com.wangweicheng.rbac.util.RedisUtils;
import com.wangweicheng.rbac.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Map<String, String> verifyCode() {
        //工具类生成验证码
        Map<String, String> map = VerifyCodeUtil.generateVerifyCode();
        //得到对应的uuid和code
        String uuid = map.get("uuid");
        String code = map.get("code");
        System.out.println(code);
        //存储到redis中
        redisUtils.set(Constants.VERIFY_CODE+ ":" +uuid, code,Constants.CODE_EXPIRES);
        map.remove("code");
        return map;
    }

    @Override
    public Employee login(LoginInfoVo loginInfoVo) {
        //判断vo是否为空
        Assert.notNull(loginInfoVo,"非法参数");
        //判断账号密码是否为空
        Employee employee = employeeMapper.login(loginInfoVo);
        Assert.notNull(employee,"姓名或密码输入错误");
        //判断验证码是否为空
        String verifyCode = redisUtils.get(Constants.VERIFY_CODE+":"+loginInfoVo.getUuid());
        Assert.notNull(verifyCode, "验证码不存在");
        //上redis查询数据，验证码，判断是否一致
        Assert.state(VerifyCodeUtil.verification(loginInfoVo.getCode(),verifyCode,true),"验证码输入错误");
        //存在把用户信息存在redis当中,将对象转换成json格式进行存储
        redisUtils.set(Constants.LOGIN_EMPLOYEE+":"+employee.getId(), JSON.toJSONString(employee));
        //把当前用户所拥有的权限集合存在redis当中，用于后序的权限拦截
        List<String> expression = permissionMapper.selectExpressionByEmpId(employee.getId());
        String expressionStr = JSON.toJSONString(expression);
        redisUtils.set(Constants.EXPRESSION +":"+ employee.getId(), expressionStr);
        return employee;
    }

    @Override
    public void logout(String userId) {
        //对redis中的数据进行删除
        String uuid = redisUtils.get(Constants.LOGIN_EMPLOYEE+":"+userId);
        Assert.notNull(uuid, "非法参数");
        //删除用户信息和对应的权限集合
        redisUtils.del(Constants.LOGIN_EMPLOYEE+":"+userId);
        redisUtils.del(Constants.EXPRESSION +":"+ userId);
    }
}
