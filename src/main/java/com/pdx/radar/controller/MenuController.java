package com.pdx.radar.controller;


import com.pdx.radar.common.DataResult;
import com.pdx.radar.service.MenuService;
import com.pdx.radar.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
@RestController
@RequestMapping("/radar/menu")
@Api(tags = "菜单接口管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单")
    @GetMapping("/menus")
    public DataResult menusByUserId(){
        return menuService.getMenusByUserId();
    }

}

