package com.wangweicheng.rbac.config;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/11
 *@Time: 17:08
 * 对拦截器进行配置
 */

import com.wangweicheng.rbac.web.intercptor.CheckLoginIntercptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private CheckLoginIntercptor checkLoginIntercptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有资源
        registry.addInterceptor(checkLoginIntercptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/login","/api/code","/api/logout");
    }
}
