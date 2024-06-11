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
import com.wangweicheng.rbac.pojo.Permission;
import com.wangweicheng.rbac.pojo.query.QueryObject;
import com.wangweicheng.rbac.service.IPermissionService;
import com.wangweicheng.rbac.util.RequirePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;


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
    
    //获取spring容器对象
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void load() {        //加载权限
        //解决重复加载的问题
        List<Permission> permissions = permissionMapper.selectAll();
        //1、从Spring容器中获取所有的controller
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(RestController.class);
        Collection<Object> controllers = map.values();
        //2、遍历所有的controller
        for(Object controller : controllers){
            //3、通过controller获取所有的方法
            Method[] methods = controller.getClass().getDeclaredMethods();
            for(Method method : methods){
                //4、通过方法获取到方法中的自定义注解@RequiredPermission
                RequirePermission annotation = method.getAnnotation(RequirePermission.class);
                //5、判断是否为空
                if(annotation == null) continue;
                //6、如果不为空，获取到其中的name和permission
                //7、创建permission对象，将name和permission封装到permission中
                Permission permission = new Permission();
                permission.setName(annotation.value()[0]);
                permission.setExpression(annotation.value()[1]);
                //不存在才插入，这里需要重写equals函数
                if(!permissions.contains(permission)){
                    //8、将permission插入到数据库中
                    permissionMapper.insert(permission);
                }
            }
        }


    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    /**
     * 根据角色id查询拥有的权限集合
     * @param id
     * @return
     */
    public List<Permission> queryByRoleId(Long id) {
        Assert.notNull(id,"非法参数");
        return permissionMapper.selectByRoleId(id);
    }
}
