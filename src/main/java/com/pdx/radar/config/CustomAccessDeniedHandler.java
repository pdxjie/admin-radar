package com.pdx.radar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdx.radar.common.DataResult;
import com.pdx.radar.exception.code.BaseResponseCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 派大星
 * @Date: 2023/02/14 2023/2/14
 * @Description: 访问接口没有权限时 自定义返回结果
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        DataResult dataResult = DataResult.getResult(BaseResponseCode.PERMISSION_IS_NOT_ENOUGH);
        out.write(new ObjectMapper().writeValueAsString(dataResult));
        out.flush();
        out.close();
    }
}
