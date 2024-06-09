package com.wangweicheng.rbac.service.impl;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/9
 *@Time: 20:17
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.mapper.PermissionMapper;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.service.IPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageInfo selectByPage(QueryObject qo) {
        PageHelper.startPage(qo.getPageNum(),qo.getPageSize());
        PageInfo pageInfo =new PageInfo<>(permissionMapper.selectForList(qo));
        return pageInfo;
    }
}
