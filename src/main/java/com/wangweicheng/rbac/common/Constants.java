package com.wangweicheng.rbac.common;

public class Constants {
    /**
     * 员工登录标识
     */
    public static final String LOGIN_EMPLOYEE = "rbac:login_employee";

    /**
     * 权限标识
     */
    public static final String EXPRESSION = "rbac:expression";

    /**
     * 验证码标识
     */
    public static final String VERIFY_CODE = "rbac:verifyCode";

    /**
     * 验证码有效期，3分钟
     */
    public static final int CODE_EXPIRES = 5 * 60;

    /**
     * 用户ID
     */
    public static final String USER_ID = "userId";
}
