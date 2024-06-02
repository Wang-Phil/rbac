package com.wangweicheng.rbac.pojo.vo;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/2
 *@Time: 22:30
 */

import com.wangweicheng.rbac.pojo.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentData {
    private Integer pageNum;
    private Integer pageSize;
    private List<Department> list;
    private Long total;
}
