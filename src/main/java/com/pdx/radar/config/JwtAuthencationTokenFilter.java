package com.pdx.radar.config;

import com.pdx.radar.common.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 派大星
 * @Date: 2023/02/13 2023/2/13
 * @Description: Jwt登录授权过滤器
 */
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        //存在token
        if (null != authHeader && authHeader.startsWith(tokenHead)){
            String accessToken = authHeader.substring(tokenHead.length());
            UserDetails userDetails = jwtUtils.getUserDetailsByToken(accessToken);
            //token存在 用户名 但是未登录
            if (null != userDetails.getUsername() && null != SecurityContextHolder.getContext().getAuthentication()){
                //登录
                UserDetails userDetail = userDetailsService.loadUserByUsername(userDetails.getUsername());
                //判断token是否有效
                if (jwtUtils.validateToken(accessToken,userDetail)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
