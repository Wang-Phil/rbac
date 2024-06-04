package com.wangweicheng.rbac.exception;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/4
 *@Time: 16:45
 */

public class BusinessExp extends RuntimeException {
    public BusinessExp() {
    }

    public BusinessExp(String message) {
        super(message);
    }
}
