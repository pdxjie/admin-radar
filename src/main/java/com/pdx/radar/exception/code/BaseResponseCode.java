package com.pdx.radar.exception.code;

/**
 * @Author: 派大星
 * @Date: 2023/02/12 2023/2/12
 * @Description:
 */
public enum BaseResponseCode implements ResponseCodeInterface{

    SUCCESS(200,"操作成功"),
    SYSTEM_ERROR(501,"系统异常，请稍后再试.."),
    METHOD_IDENTITY_ERROR(402,"数据校验异常"),
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
