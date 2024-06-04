package com.wangweicheng.rbac.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoVo {
    private String username;// 登录名称
    private String password;// 密码
    private String code;// 验证码
    private String uuid; // 唯一标识
}
