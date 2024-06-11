package com.wangweicheng.rbac.service;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/11
 *@Time: 15:04
 */

import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.vo.LoginInfoVo;

import java.util.Map;

public interface ILoginService {
    Map<String, String> verifyCode();

    Employee login(LoginInfoVo loginInfoVo);

    void logout(String userId);
}
