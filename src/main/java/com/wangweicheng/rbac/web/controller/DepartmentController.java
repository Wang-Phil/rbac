package com.wangweicheng.rbac.web.controller;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/2
 *@Time: 22:15
 */

import com.wangweicheng.rbac.pojo.Department;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.DepartmentData;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/list")
    public JsonResult list(QueryObject queryObject) {
        PageResult<Department> result = departmentService.selectByPage(queryObject);
        DepartmentData departmentData = new DepartmentData(result.getPageNum(), result.getPageSize(), result.getList(), result.getTotal());
        return JsonResult.success(departmentData);
    }
}
