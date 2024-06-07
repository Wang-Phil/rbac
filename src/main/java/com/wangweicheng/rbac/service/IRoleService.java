package com.wangweicheng.rbac.service;
import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.pojo.Role;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.RoleVo;

import java.util.List;

public interface IRoleService {
    List<Role> selectAll();
    void deleteById(Long id);
    void saveOrUpdate(RoleVo roleVo);
    Role selectById(Long id);
    PageInfo selectByPage(QueryObject qo);

    List<Role> queryRolesByEmployeeId(Long employeeId);
}
