package com.wangweicheng.rbac.service;


import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.pojo.Department;
import com.wangweicheng.rbac.pojo.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    PageInfo selectByPage(QueryObject qo);
}
