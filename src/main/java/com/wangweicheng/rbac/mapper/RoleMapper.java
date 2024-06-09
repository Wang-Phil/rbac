package com.wangweicheng.rbac.mapper;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/7
 *@Time: 14:38
 */

import com.wangweicheng.rbac.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteById(Long id);
    int insert(Role record);
    Role selectById(Long id);
    List<Role> selectAll();
    int updateById(Role record);
    List<Role> selectForList();
    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);
    int deleteRelation(Long roleId);
    List<Role> selectRolesByEmployee(Long employeeId);
}
