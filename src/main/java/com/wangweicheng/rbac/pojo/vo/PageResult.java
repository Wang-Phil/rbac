package com.wangweicheng.rbac.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    //数据从前端传递
    private int pageNum;//当前页
    private int pageSize;// 每页显示多少条
    //数据从数据库中获取
    private List<T> list;// 每页显示数据
    private long total;;// 总条数
}
