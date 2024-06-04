package com.wangweicheng.rbac.mapper;

import com.wangweicheng.rbac.pojo.Employee;
import com.wangweicheng.rbac.pojo.query.EmployeeQueryObject;
import com.wangweicheng.rbac.pojo.vo.LoginInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    //根据id查询员工信息
    Employee selectById(Long id);
    //查询所有员工
    List<Employee> selectAll();
    //根据员工id删除员工
    int deleteById(Long id);
    //添加员工
    int insert(Employee employee);
    //根据员工id更新员工
    int updateById(Employee employee);
    // 更新员工管理员状态
    int updateStateById(@Param("id") Long id,@Param("admin") boolean admin);
    // 查询分页数据
    List<Employee> queryForList(EmployeeQueryObject qo);
    // 登录
    Employee login(LoginInfoVo loginInfoVo);
    // 维护员工和角色的关系
    void insertRelation(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);
    // 维护员工和角色的关系
    void deleteRelation(Long employeeId);
}
