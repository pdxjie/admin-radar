package com.pdx.radar.exception;

import com.pdx.radar.exception.code.ResponseCodeInterface;

/**
 * @Author: 派大星
 * @Date: 2023/02/12 2023/2/12
 * @Description:
 */
public class BusinessException extends RuntimeException{

    /*
     * 提示编码
     * */
    private final int code;

    /*
     * 后端提示语
     * */
    private final String msg;

    public BusinessException(int code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ResponseCodeInterface responseCodeInterface){
        this(responseCodeInterface.getCode(),responseCodeInterface.getMsg());
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}