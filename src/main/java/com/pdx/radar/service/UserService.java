package com.pdx.radar.service;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdx.radar.pojo.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
public interface UserService extends IService<User> {

    DataResult login(LoginVo loginVo, HttpServletRequest request);

}
