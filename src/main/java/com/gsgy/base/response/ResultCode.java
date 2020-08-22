package com.gsgy.base.response;


/**
 * 全局通用响应状态
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(0,"操作成功"),
    INSERT_SUCCESS(0,"新增成功"),
    MODIFY_SUCCESS(0,"修改成功"),
    DELETE_SUCCESS(0,"删除成功"),

    /* 错误状态码 */
    FAIL(-1,"操作失败"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数格式错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录，请先登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),

    /* 权限错误：30001-39999 */
    PERMISSION_UNAUTHENTICATED(70001,"此操作需要登陆系统！"),
    PERMISSION_UNAUTHORISE(70002,"权限不足，无权操作！"),
    PERMISSION_EXPIRE(70003,"登录状态过期！"),
    PERMISSION_TOKEN_EXPIRED(70004, "token已过期"),
    PERMISSION_LIMIT(70005, "访问次数受限制"),
    PERMISSION_TOKEN_INVALID(70006, "无效token"),
    PERMISSION_SIGNATURE_ERROR(70007, "签名失败"),

    /* 业务错误：40001-49999 */

    /* 系统异常：90001-99999 */
    SYSTEM_INNER_ERROR(90001,"系统发生未知异常");


    //操作代码
    int code;
    //提示信息
    String message;
    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int code() { return code;}

    public String message() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
