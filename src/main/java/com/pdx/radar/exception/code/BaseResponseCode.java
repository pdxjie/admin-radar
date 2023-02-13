package com.pdx.radar.exception.code;

/**
 * @Author: 派大星
 * @Date: 2023/02/12 2023/2/12
 * @Description:
 */
public enum BaseResponseCode implements ResponseCodeInterface{

    SUCCESS(200,"操作成功"),
    SYSTEM_ERROR(501,"系统异常，请稍后再试.."),
    METHOD_IDENTITY_ERROR(402,"尚未登录，请登录"),
    PERMISSION_IS_NOT_ENOUGH(403,"权限不足，请联系管理员"),
    USER_IS_NOT_EXIST_OR_ERROR(201,"用户信息不存在或密码错误"),
    ACCOUNT_LOCKED(202,"账号被禁用，请联系管理员"),
    ;

    /*
     * 响应码
     * */
    private int code;

    /*
     * 提示
     * */
    private String msg;

    BaseResponseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
