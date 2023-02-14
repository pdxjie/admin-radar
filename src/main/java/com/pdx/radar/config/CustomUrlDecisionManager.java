package com.pdx.radar.config;

import com.pdx.radar.exception.BusinessException;
import com.pdx.radar.exception.code.BaseResponseCode;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: 派大星
 * @Date: 2023/02/14 2023/2/14
 * @Description: 权限拦截
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            //当前url所需的角色
            String needRole = configAttribute.getAttribute();
            //判断角色是否登录即可访问
            if ("ROLE_LOGIN".equals(needRole)){
                //未登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new BusinessException(BaseResponseCode.METHOD_IDENTITY_ERROR);
                }else {
                    return;
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                //判断用户角色是否为url所需角色
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new BusinessException(BaseResponseCode.PERMISSION_IS_NOT_ENOUGH);
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
