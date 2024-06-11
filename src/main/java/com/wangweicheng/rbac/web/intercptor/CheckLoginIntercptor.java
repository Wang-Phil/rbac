package com.wangweicheng.rbac.web.intercptor;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/11
 *@Time: 16:47
 * 拦截器
 */

import com.alibaba.fastjson.JSON;
import com.wangweicheng.rbac.common.Constants;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.util.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CheckLoginIntercptor implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;


    @Override  //运行前运行，返回值为true，放行，返回值为false，不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设定数据响应格式
        response.setContentType("application/json;charset=utf-8");
        if(!(handler instanceof HandlerMethod)){ // 放行跨域的二次校验
            return true;
        }
        //通过request获取head中的useid
        String userId = request.getHeader(Constants.USER_ID);
        Assert.notNull(userId,"非法操作");
        //上redis查询数据
        String objJson =  redisUtils.get(Constants.LOGIN_EMPLOYEE + ":" + userId);
        System.out.println(objJson);
        //如果数据为空，响应数据
        if(StringUtils.isEmpty(objJson)){
            JsonResult result = new JsonResult(401, "操作失败", "fail", null);
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }
        //如果不为空，放行
        return true;
    }
}
