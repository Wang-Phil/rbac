package com.wangweicheng.rbac.pojo;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/7
 *@Time: 14:36
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String name;
    private String sn;
}
