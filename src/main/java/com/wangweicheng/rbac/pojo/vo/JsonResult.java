package com.wangweicheng.rbac.pojo.vo;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/2
 *@Time: 22:20
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private Integer code;
    private String msg;
    private String stauts;
    private Object data;

    public static JsonResult success() {
        JsonResult result = new JsonResult(200, "操作成功", "success", null);
        return result;
    }

    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult(200, "操作成功", "success", data);
        return result;
    }

    public static JsonResult fail() {
        JsonResult result = new JsonResult(500, "操作失败", "fail", null);
        return result;
    }

    public static JsonResult fail(String msg) {
        JsonResult result = new JsonResult(500, msg, "fail", null);
        return result;
    }
}
