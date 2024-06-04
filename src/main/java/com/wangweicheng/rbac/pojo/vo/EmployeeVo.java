package com.wangweicheng.rbac.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo {
    private Long id;// 员工id
    private String name; // 员工名字
    private String password;// 密码
    private String email;// 邮箱
    private int age;// 年龄
    private long deptId; // 部门id
}
