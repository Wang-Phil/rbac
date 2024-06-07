package com.wangweicheng.rbac.web.controller;
import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.query.EmployeeQueryObject;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.AdminStateVo;
import com.wangweicheng.rbac.pojo.vo.EmployeeRoleVo;
import com.wangweicheng.rbac.pojo.vo.JsonResult;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/6
 *@Time: 21:05
 */

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 分页查询
     * @param queryObject
     * @return
     */
    @GetMapping("/list")
    public JsonResult list(EmployeeQueryObject queryObject) {
        PageResult result = employeeService.selectByPage(queryObject);
//        PageResult<Employee> pageResult = new PageResult(result.getPageNum(), result.getPageSize(), result.getList(), result.getTotal());
        return JsonResult.success(result);
    }

    /**
     * 删除员工
     * @return
     */
    @DeleteMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * 新增或者删除员工
     * @param employeeRoleVo
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody EmployeeRoleVo employeeRoleVo) {
        employeeService.saveOrUpdate(employeeRoleVo);
        return JsonResult.success();
    }

    /**
     * 查询单个员工
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public JsonResult get(@PathVariable("id") Long id) {
        return JsonResult.success(employeeService.selectById(id));
    }

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping("/listAll")
    public JsonResult listAll() {
        return JsonResult.success(employeeService.selectAll());
    }

    /**
     * 更新员工管理员状态
     * @param adminStateVo
     * @return
     */
    @PostMapping("/updateState")
    public JsonResult updateState(@RequestBody AdminStateVo adminStateVo) {
        employeeService.updateStateById(adminStateVo);
        return JsonResult.success();
    }
}
