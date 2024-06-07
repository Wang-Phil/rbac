package com.wangweicheng.rbac.pojo.vo;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/7
 *@Time: 20:09
 */

import com.wangweicheng.rbac.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private Role role;
    private Long[] permissionIds;
}

