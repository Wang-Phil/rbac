package com.wangweicheng.rbac.web.common;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/4
 *@Time: 16:17
 * 统一异常处理
 */

import com.wangweicheng.rbac.pojo.vo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExpHandlerController  {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult exceptionHandler(Exception e){
        e.printStackTrace();
        return JsonResult.fail(e.getMessage());
    }
}
