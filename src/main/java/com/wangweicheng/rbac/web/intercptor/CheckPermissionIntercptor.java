package com.wangweicheng.rbac.web.intercptor;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/11
 *@Time: 17:29
 */

import com.alibaba.fastjson.JSON;
import com.wangweicheng.rbac.common.Constants;
import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.util.RedisUtils;
import com.wangweicheng.rbac.util.RequirePermission;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Component
public class CheckPermissionIntercptor implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置返回的类型
        response.setContentType("application/json;charset=utf-8");
        //获取userid
        String userId = request.getHeader(Constants.USER_ID);
        //从redis中获取用户信息
        String objJson = redisUtils.get(Constants.LOGIN_EMPLOYEE + ":" + userId);
        //将其转换为employee类型
        Employee employee = JSON.parseObject(objJson, Employee.class);
        if(employee == null){return false;}
        //判断是否是超管,如果是直接放行
        if(employee.isAdmin()) return true;
        //获取方法
        //拦截器的参数的两种类型
        //当访问的是控制器的方法的时候，类型就是handlerMethod
        //放访问的事静态资源的时候就不是HandlerMethod
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //获取方法上的注解
            RequirePermission annotation = method.getAnnotation(RequirePermission.class);
            //判断注解是否为空，为空表示不需要权限就可以进行访问
            if(annotation == null){return true;}
            //获取redis中的权限表达式集合
            String expressionJson = redisUtils.get(Constants.EXPRESSION + ":" + userId);
            ArrayList<String> expressions = JSON.parseObject(expressionJson, ArrayList.class);
            //判断是否在集合中
                //先获取到权限列表
            String expression = annotation.value()[1];
                //然后判断是否在集合中
            if(expressions.contains(expression)){
                //有权限
                return true;
            }
        }
        //无权限
        JsonResult result = new JsonResult(401, "操作失败", "fail", null);
        response.getWriter().write(JSON.toJSONString(result));
        return false;
    }
}
