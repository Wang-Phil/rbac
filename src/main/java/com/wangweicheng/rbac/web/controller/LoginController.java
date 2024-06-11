package com.wangweicheng.rbac.web.controller;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/11
 *@Time: 15:02
 */


import com.wangweicheng.rbac.common.Constants;
import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.pojo.vo.LoginInfoVo;
import com.wangweicheng.rbac.service.ILoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping("/code")
    public JsonResult code(){
        Map<String, String> map = loginService.verifyCode();
        return JsonResult.success(map);
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginInfoVo loginInfoVo){
        Employee employee = loginService.login(loginInfoVo);
        return JsonResult.success(employee);
    }

    @GetMapping("/logout")
    public JsonResult logout(HttpServletRequest request){
        //获取到请求头中的userid
        loginService.logout(request.getHeader(Constants.USER_ID));
        return JsonResult.success();
    }
}
