package com.pdx.radar.exception.handler;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.exception.BusinessException;
import com.pdx.radar.exception.code.BaseResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author: 派大星
 * @Date: 2023/02/12 2023/2/12
 * @Description:
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public DataResult exception(Exception e){
        log.error("Exception====>{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public DataResult businessException(BusinessException e){
        log.error("businessException ====>{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(e.getCode(),e.getMsg());
    }


    /**
     * 框架异常
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    <T> DataResult<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return createValidExceptionResp(errors);
    }
    private <T> DataResult<T> createValidExceptionResp(List<ObjectError> errors) {
        String[] msgs = new String[errors.size()];
        int i = 0;
        for (ObjectError error : errors) {
            msgs[i] = error.getDefaultMessage();
            log.info("msg={}",msgs[i]);
            i++;
        }
        return DataResult.getResult(BaseResponseCode.METHOD_IDENTITY_ERROR.getCode(), msgs[0]);
    }

    //@ExceptionHandler(UnauthorizedException.class)
    //public DataResult unauthorizedException(UnauthorizedException e){
    //    log.error("UnauthorizedException,{},{}",e.getLocalizedMessage(),e);
    //    return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    //}


}
