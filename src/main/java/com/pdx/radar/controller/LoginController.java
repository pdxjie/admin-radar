package com.pdx.radar.controller;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.vo.LoginVo;
import com.pdx.radar.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 派大星
 * @Date: 2023/02/13 2023/2/13
 * @Description: 登录前端控制器
 */
@RestController
@RequestMapping("/radar")
@Api(tags = "用户登录接口管理")
public class LoginController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "登录接口 返回token")
    @PostMapping("/login")
    public DataResult login(LoginVo loginVo, HttpServletRequest request){
        return userService.login(loginVo,request);
    }
}
