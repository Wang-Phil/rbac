package com.wangweicheng.rbac.service;


import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.pojo.Department;
import com.wangweicheng.rbac.pojo.Permission;
import com.wangweicheng.rbac.pojo.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    PageInfo selectByPage(QueryObject qo);
    public List<Permission> queryByRoleId(Long id);
    void load();
}
