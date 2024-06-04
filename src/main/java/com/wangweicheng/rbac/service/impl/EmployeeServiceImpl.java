package com.wangweicheng.rbac.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.mapper.EmployeeMapper;
import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.query.EmployeeQueryObject;
import com.wangweicheng.rbac.pojo.vo.AdminStateVo;
import com.wangweicheng.rbac.pojo.vo.EmployeeRoleVo;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }
    @Override
    public PageResult<Employee> selectByPage(EmployeeQueryObject qo) {
        PageHelper.startPage(qo.getPageNum(),qo.getPageSize());
        PageInfo pageInfo=new PageInfo(employeeMapper.queryForList(qo));
        PageResult pageResult=new PageResult();
        BeanUtils.copyProperties(pageInfo,pageResult);
        return pageResult;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Assert.notNull(id,"非法參數");
        Assert.state(employeeMapper.deleteById(id)>0,"删除失败");
        employeeMapper.deleteRelation(id);
    }

    @Override
    @Transactional
    public void saveOrUpadate(EmployeeRoleVo employeeRoleVo) {
        Assert.notNull(employeeRoleVo,"非法參數");
        Assert.notNull(employeeRoleVo.getEmployee(),"非法參數");
        if(employeeRoleVo.getEmployee().getId()==null){
            // 添加员工
            save(employeeRoleVo.getEmployee(),employeeRoleVo.getRoleIds());
        }else{
            // 更新员工信息
            update(employeeRoleVo.getEmployee(),employeeRoleVo.getRoleIds());
        }
    }

    @Transactional
    public void save(Employee employee, Long[] roleIds) {
        // 保存员工信息
        employeeMapper.insert(employee);
        // 保存员工角色表 employee_role
        saveRelation(employee, roleIds);

    }

    @Transactional
    public void update(Employee employee, Long[] roleIds) {
        //更新员工信息
        employeeMapper.updateById(employee);
        //更新员工角色表(先删后添加)
        employeeMapper.deleteRelation(employee.getId());
        saveRelation(employee, roleIds);
    }

    @Override
    public void updateStateById(AdminStateVo adminStateVo) {
        Assert.notNull(adminStateVo,"非法參數");
        Assert.state(  employeeMapper.updateStateById(adminStateVo.getId(),adminStateVo.isAdmin())>0,
                "更新状态失败");
    }


    private void saveRelation(Employee employee, Long[] roleIds) {
        if(roleIds != null && roleIds.length>0){
            for (Long roleId : roleIds) {
                employeeMapper.insertRelation(employee.getId(),roleId);
            }
        }
    }

    @Override
    public Employee selectById(Long id) {
        Assert.notNull(id,"非法參數");
        return employeeMapper.selectById(id);
    }
}
