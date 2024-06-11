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
import com.wangweicheng.rbac.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permission")
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
public class PermissionController {
    @Autowired
    private PermissionServiceImpl permissionService;

    /**
     * 权限列表
     * @param queryObject
     * @return
     */
    @GetMapping("/list")
    public JsonResult list(EmployeeQueryObject queryObject) {
        PageInfo result = permissionService.selectByPage(queryObject);
        PageResult<Permission> pageResult = new PageResult(result.getPageNum(), result.getPageSize(), result.getList(), result.getTotal());
        return JsonResult.success(pageResult);
    }

    /**
     * 加载权限
     * @return
     */
    @PostMapping("/load")
    public JsonResult load() {
        permissionService.load();
        return JsonResult.success();
    }

    @GetMapping("queryPermission/{id}")
    public JsonResult queryPermission(@PathVariable Long id) {
        List<Permission> permissions = permissionService.queryByRoleId(id);
        return JsonResult.success(permissions);
    }
    @GetMapping("/listAll")
    @ResponseBody
    @RequirePermission({"权限列表","permission:listAll"})
    public JsonResult listAll() {
        return JsonResult.success(permissionService.listAll());
    }


}
