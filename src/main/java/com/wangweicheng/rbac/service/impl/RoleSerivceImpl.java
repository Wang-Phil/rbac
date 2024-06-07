package com.wangweicheng.rbac.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangweicheng.rbac.mapper.RoleMapper;
import com.wangweicheng.rbac.pojo.Role;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.pojo.vo.PageResult;
import com.wangweicheng.rbac.pojo.vo.RoleVo;
import com.wangweicheng.rbac.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RoleSerivceImpl implements IRoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleSerivceImpl.class);
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
    /**
     * 分页查询
     * @param qo
     * @return
     */
    @Override
    public PageInfo selectByPage(QueryObject qo) {
        PageHelper.startPage(qo.getPageNum(),qo.getPageSize());
        PageInfo pageInfo=new PageInfo<>(roleMapper.selectForList());
/*        PageResult pageResult = new PageResult();
        BeanUtils.copyProperties(pageInfo,pageResult);*/
        return pageInfo;
    }

    @Override
    public List<Role> queryRolesByEmployeeId(Long employeeId) {
        // 参数校验
        Assert.notNull(employeeId,"非法参数");
        return roleMapper.selectRolesByEmployee(employeeId);
    }

    /**
     * 通过id删除角色
     * @param id
     * @return
     */
    @Override
    public void deleteById(Long id) {
        log.info("删除的Id为{}", id);
       Assert.state(roleMapper.deleteById(id)>0,"删除失败");
        // 删除角色关联权限信息
        roleMapper.deleteRelation(id);
    }

    @Override
    public void saveOrUpdate(RoleVo roleVo) {
        //参数校验
        Assert.notNull(roleVo, "非法参数");
        Assert.notNull(roleVo.getRole(), "非法参数");
        if(roleVo.getRole().getId()==null){
            //增加角色信息
            save(roleVo.getRole(), roleVo.getPermissionIds());
        }else{
            //更新角色信息
            update(roleVo.getRole(), roleVo.getPermissionIds());
        }
    }

    private void update(Role role, Long[] permissionIds) {
        //更新角色表
        roleMapper.updateById(role);
        //更新权限表，先删除，然后再增加
        roleMapper.deleteRelation(role.getId());
        insertRelation(role,permissionIds);
    }

    public void save(Role role, Long[] permissionIds) {
        //插入角色表
        roleMapper.insert(role);
        //保存角色-权限表
        insertRelation(role, permissionIds);
    }

    private void insertRelation(Role role, Long[] permissionIds) {
        if(permissionIds!=null&&permissionIds.length>0){
            for(Long permissionId:permissionIds){
                roleMapper.insertRelation(role.getId(), permissionId);
            }
        }
    }

    @Override
    public Role selectById(Long id) {
        Assert.notNull(id,"非法参数");
        return roleMapper.selectById(id);
    }
}
