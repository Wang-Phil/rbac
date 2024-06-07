package com.wangweicheng.rbac.web.controller;

import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.pojo.Department;
import com.wangweicheng.rbac.pojo.Role;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.AdminStateVo;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.pojo.vo.RoleVo;
import com.wangweicheng.rbac.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/6
 *@Time: 21:05
 */

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 分页查询
     *
     * @param queryObject
     * @return
     */
    @GetMapping("/list")
    public JsonResult list(QueryObject queryObject) {
        PageInfo result = roleService.selectByPage(queryObject);
        PageResult<Department> pageResult = new PageResult(result.getPageNum(), result.getPageSize(), result.getList(), result.getTotal());
        return JsonResult.success(pageResult);
    }

    /**
     * 删除角色
     *
     * @return
     */
    @DeleteMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * 新增或者删除角色
     *
     * @param role
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody RoleVo roleVo) {
        roleService.saveOrUpdate(roleVo);
        return JsonResult.success();
    }

    /**
     * 查询单个角色
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public JsonResult get(@PathVariable("id") Long id) {
        return JsonResult.success(roleService.selectById(id));
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @GetMapping("/listAll")
    public JsonResult listAll() {
        return JsonResult.success(roleService.selectAll());
    }

    @GetMapping("query/{employeeId}")
    public JsonResult query(@PathVariable("employeeId") Long employeeId) {
        return JsonResult.success(roleService.queryRolesByEmployeeId(employeeId));
    }


}


