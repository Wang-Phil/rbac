package com.wangweicheng.rbac.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 */
@Target(ElementType.METHOD) //指定注解的使用位置    方法上
@Retention(RetentionPolicy.RUNTIME) //指定注解保存时期
public @interface RequirePermission {
    //注解内容
    String[] value();
}
