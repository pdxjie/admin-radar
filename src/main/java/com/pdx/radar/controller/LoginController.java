package com.pdx.radar.controller;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.User;
import com.pdx.radar.pojo.vo.LoginVo;
import com.pdx.radar.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
    public DataResult login(@RequestBody LoginVo loginVo, HttpServletRequest request){
        return userService.login(loginVo,request);
    }

    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/userInfo")
    public DataResult userInfo(Principal principal){
        if (null == principal){
            return null;
        }
        String username = principal.getName();
        User user = userService.userInfoByToken(username);
        return DataResult.success(user);
    }

    @ApiOperation(value = "用户退出登录")
    @PostMapping("/logout")
    public DataResult logout(){
        return DataResult.success();
    }
}
