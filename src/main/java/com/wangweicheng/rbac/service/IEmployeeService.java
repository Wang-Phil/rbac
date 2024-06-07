package com.wangweicheng.rbac.service;



import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.query.EmployeeQueryObject;
import com.wangweicheng.rbac.pojo.vo.AdminStateVo;
import com.wangweicheng.rbac.pojo.vo.EmployeeRoleVo;
import com.wangweicheng.rbac.pojo.vo.PageResult;

import java.util.List;

public interface IEmployeeService {
    List<Employee> selectAll();
    PageResult<Employee> selectByPage(EmployeeQueryObject qo);
    void deleteById(Long id);
    void saveOrUpdate(EmployeeRoleVo employeeRoleVo);
    void updateStateById(AdminStateVo adminStateVo);
    Employee selectById(Long id);
}
