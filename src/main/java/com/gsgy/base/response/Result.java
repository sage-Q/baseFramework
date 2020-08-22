package com.gsgy.base.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 统一响应结果对象
 */
@JsonSerialize
public class Result<T> {

    //操作代码
    int code;

    //提示信息
    String message;

    //结果数据
    T data;

    /**
     * 创建结果对象
     * @param resultCode 结果编码对象
     * @return Result对象
     */
    public static <T> Result createResult(ResultCode resultCode){
        return new Result(resultCode);
    }

    /**
     * 创建结果对象
     * @param resultCode 结果编码对象
     * @param data 数据
     * @return Result对象
     */
    public static <T> Result createResult(ResultCode resultCode,T data){
        return new Result(resultCode,data);
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
