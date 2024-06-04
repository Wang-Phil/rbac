package com.wangweicheng.rbac.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;// 员工id
    private String name; // 员工名字
    private String password;// 密码
    private String email;// 邮箱
    private int age;// 年龄
    private boolean admin; // 是否是管理员
    private Department department; // 部门
}
