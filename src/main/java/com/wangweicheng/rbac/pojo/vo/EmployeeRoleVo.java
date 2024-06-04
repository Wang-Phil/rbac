package com.wangweicheng.rbac.pojo.vo;


import com.wangweicheng.rbac.pojo.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRoleVo {
    private Employee employee;
    private Long[] roleIds;
}
