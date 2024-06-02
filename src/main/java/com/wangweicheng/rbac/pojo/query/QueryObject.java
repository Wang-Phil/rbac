package com.wangweicheng.rbac.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryObject {
    private int pageNum=1;// 当前页
    private int pageSize=5;// 每页显示多少条
    public int getStart(){
        return (pageNum-1)*pageSize;
    }
}
