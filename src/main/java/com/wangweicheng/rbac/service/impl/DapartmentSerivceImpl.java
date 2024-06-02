package com.wangweicheng.rbac.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.mapper.DepartmentMapper;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.pojo.Department;
import com.wangweicheng.rbac.service.IDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class DapartmentSerivceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }
    /**
     * 分页查询
     * @param qo
     * @return
     */
    @Override
    public PageResult<Department> selectByPage(QueryObject qo) {
        PageHelper.startPage(qo.getPageNum(),qo.getPageSize());
        PageInfo pageInfo=new PageInfo<>(departmentMapper.queryForList());
        PageResult pageResult = new PageResult();
        BeanUtils.copyProperties(pageInfo,pageResult);
        return pageResult;
    }

    /**
     * 通过id删除部门
     * @param id
     * @return
     */
    @Override
    public void deleteById(Long id) {
       Assert.state(departmentMapper.deleteById(id)>0,"删除失败");
    }

    @Override
    public void saveOrUpdate(Department department) {
        // 添加部门信息
        if (department.getId() == null) {
            Assert.state(departmentMapper.insert(department)>0,
                    "添加失败");
            return;
        }
        // 更新部门信息
        Assert.state( departmentMapper.updateById(department)>0,
                "更新失败");
    }

    @Override
    public Department selectById(Long id) {
        Assert.notNull(id,"非法参数");
        return departmentMapper.selectById(id);
    }
}
