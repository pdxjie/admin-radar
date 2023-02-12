package com.pdx.radar.common;

import com.pdx.radar.exception.code.BaseResponseCode;
import com.pdx.radar.exception.code.ResponseCodeInterface;
import lombok.Data;

/**
 * @Author: 派大星
 * @Date: 2023/02/12 2023/2/12
 * @Description: 响应类
 */
@Data
public class DataResult<T> {
    //响应码
    private int code;
    //响应信息
    private String msg;
    //响应数据
    private T data;

    public DataResult(int code,T data){
        this.code = code;
        this.data = data;
        this.msg = null;
    }

    public DataResult(int code,String msg,T data){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public DataResult(){
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public DataResult(T data){
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    public DataResult(ResponseCodeInterface responseCodeInterface){
        this.data = null;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }

    public DataResult(ResponseCodeInterface responseCodeInterface,T data){
        this.data = data;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }

    /**
     * 操作成功
     * @return
     */
    public static DataResult success(){
        return new DataResult();
    }

    /**
     * 操作成功 data不为null
     * @param data
     * @param <T>
     * @return
     */
    public static <T> DataResult success(T data){
        return new DataResult(data);
    }

    /**
     * 自定义返回操作
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T>DataResult getResult(int code,String msg,T data){
        return new DataResult(code,msg,data);
    }

    /**
     * 自定义返回，data为null
     * @param code
     * @param msg
     * @return
     */
    public static DataResult getResult(int code,String msg){
        return new DataResult(code,msg);
    }

    /**
     * 自定义返回 参数一般是异常code 枚举 data为空
     * @param baseResponseCode
     * @return
     */
    public static DataResult getResult(BaseResponseCode baseResponseCode){
        return new DataResult(baseResponseCode);
    }

    /**
     * 自定义返回 参数一般为code枚举 data
     * @param responseCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T>DataResult getResult(BaseResponseCode responseCode,T data){
        return new DataResult(responseCode,data);
    }
}