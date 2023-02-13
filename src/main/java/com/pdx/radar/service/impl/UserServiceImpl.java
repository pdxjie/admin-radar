package com.pdx.radar.service.impl;

import com.pdx.radar.pojo.User;
import com.pdx.radar.mapper.UserMapper;
import com.pdx.radar.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
