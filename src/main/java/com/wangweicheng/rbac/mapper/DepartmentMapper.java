package com.wangweicheng.rbac.mapper;


import com.wangweicheng.rbac.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    //根据id查询部门
    Department selectById(Long id);
    //查询所有部门
    List<Department> selectAll();
    //根据部门id删除部门
    int deleteById(Long id);
    //添加部门
    int insert(Department department);
    //根据部门id更新部门
    int updateById(Department department);
    // 查询分页数据
    List<Department> queryForList();
}
