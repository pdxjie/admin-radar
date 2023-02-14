package com.pdx.radar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 派大星
 * @Date: 2023/02/14 2023/2/14
 * @Description:
 */
@RestController
public class TestController {


    @GetMapping("/test/hello")
    public String hello(){
        return "/test/hello";
    }


    @GetMapping("/radar/user/ceshi")
    public String ceshi(){
        return "/radar/user/ceshi";
    }
}
