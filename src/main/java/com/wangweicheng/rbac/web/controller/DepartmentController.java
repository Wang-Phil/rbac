package com.wangweicheng.rbac.web.controller;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/2
 *@Time: 22:15
 */

import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.pojo.Department;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.service.IDepartmentService;
import com.wangweicheng.rbac.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    /**
     * 分页查询
     * @param queryObject
     * @return
     */
    @GetMapping("/list")
    @RequirePermission({"部门列表","department:list"})
    public JsonResult list(QueryObject queryObject) {
        PageInfo result = departmentService.selectByPage(queryObject);
        PageResult<Department> pageResult = new PageResult(result.getPageNum(), result.getPageSize(), result.getList(), result.getTotal());
        return JsonResult.success(pageResult);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    @RequirePermission({"部门删除","department:delete"})
    public JsonResult delete(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * 新增或者删除部门
     * @param department
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @RequirePermission({"部门新增或编辑","department:saveOrUpdate"})
    public JsonResult saveOrUpdate(@RequestBody Department department) {
        departmentService.saveOrUpdate(department);
        return JsonResult.success();
    }

    /**
     * 查询单个部门
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @RequirePermission({"查询单个部门","department:info"})
    public JsonResult get(@PathVariable("id") Long id) {
        return JsonResult.success(departmentService.selectById(id));
    }

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping("/listAll")
    @RequirePermission({"查询所有部门","department:listAll"})
    public JsonResult listAll() {
        return JsonResult.success(departmentService.selectAll());
    }
}
