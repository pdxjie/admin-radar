package com.pdx.radar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdx.radar.common.DataResult;
import com.pdx.radar.exception.code.BaseResponseCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: 派大星
 * @Date: 2023/02/14 2023/2/14
 * @Description: 未登录或者Token失效 自定义返回结果
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        DataResult dataResult = DataResult.getResult(BaseResponseCode.METHOD_IDENTITY_ERROR);
        out.write(new ObjectMapper().writeValueAsString(dataResult));
        out.flush();
        out.close();
    }
}
