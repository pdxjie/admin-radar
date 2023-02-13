package com.pdx.radar.service.impl;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.User;
import com.pdx.radar.mapper.UserMapper;
import com.pdx.radar.pojo.vo.LoginVo;
import com.pdx.radar.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserDetailsService userDetailsService;


    /**
     * 登录之后返回token
     * @param loginVo
     * @param request
     * @return
     */
    @Override
    public DataResult login(LoginVo loginVo, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());
        if (null == userDetails){

        }
        return null;
    }
}
