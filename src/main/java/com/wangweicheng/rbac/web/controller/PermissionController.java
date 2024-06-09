package com.wangweicheng.rbac.web.controller;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/9
 *@Time: 20:21
 */

import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.pojo.Permission;
import com.wangweicheng.rbac.pojo.query.EmployeeQueryObject;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.service.IPermissionService;
import com.wangweicheng.rbac.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/permission")
public class PermissionController {
    @Autowired
    private PermissionServiceImpl permissionService;
    @GetMapping("/list")
    public JsonResult list(EmployeeQueryObject queryObject) {
        PageInfo result = permissionService.selectByPage(queryObject);
        PageResult<Permission> pageResult = new PageResult(result.getPageNum(), result.getPageSize(), result.getList(), result.getTotal());
        return JsonResult.success(pageResult);
    }
}
