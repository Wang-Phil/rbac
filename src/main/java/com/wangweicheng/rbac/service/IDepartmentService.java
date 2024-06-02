package com.wangweicheng.rbac.service;



import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.pojo.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> selectAll();
    void deleteById(Long id);
    void saveOrUpdate(Department department);
    Department selectById(Long id);
    PageResult<Department> selectByPage(QueryObject qo);
}
