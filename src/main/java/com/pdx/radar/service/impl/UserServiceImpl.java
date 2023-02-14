package com.pdx.radar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdx.radar.common.DataResult;
import com.pdx.radar.common.JwtUtils;
import com.pdx.radar.exception.BusinessException;
import com.pdx.radar.exception.code.BaseResponseCode;
import com.pdx.radar.pojo.Menu;
import com.pdx.radar.pojo.User;
import com.pdx.radar.mapper.UserMapper;
import com.pdx.radar.pojo.vo.LoginVo;
import com.pdx.radar.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private JwtUtils jwtUtils;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 登录之后返回token
     * @param loginVo
     * @param request
     * @return
     */
    @Override
    public DataResult login(LoginVo loginVo, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());

        String captcha = (String) redisTemplate.opsForValue().get("captcha-result");
        Boolean isExist = redisTemplate.hasKey("captcha-result");
        //判断验证码是否失效
        if (!isExist){
            throw new BusinessException(BaseResponseCode.CODE_IS_NOT_EXISTS);
        }

        //判断验证码结果是否正确
        if (!loginVo.getCaptcha().equals(captcha)){
            throw new BusinessException(BaseResponseCode.CAPTCHA_CODE_IS_ERROR);
        }

        //判断用户是否存在
        if (null == userDetails || !passwordEncoder.matches(loginVo.getPassword(),userDetails.getPassword())){
            throw new BusinessException(BaseResponseCode.USER_IS_NOT_EXIST_OR_ERROR);
        }
        //判断是否被禁用
        if (userDetails.isEnabled()){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCKED);
        }

        //更新Security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        //将其放在Security的全局中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = jwtUtils.generateToken(userDetails);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("token",token);
        resultMap.put("tokenHead",tokenHead);
        return DataResult.success(resultMap);
    }

    @Override
    public User userInfoByToken(String username) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("enabled",false));
        user.setPassword(null);
        return user;
    }
}
