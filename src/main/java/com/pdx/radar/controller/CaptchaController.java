package com.pdx.radar.controller;

import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 派大星
 * @Date: 2023/02/14 2023/2/14
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/radar")
@Api(tags = "验证码管理接口")
public class CaptchaController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 生成验证码的接口
     * @param response  用于输出流
     */
    @GetMapping("/captcha")
    @ApiOperation(value = "生成验证码")
    public void verifyCode(HttpSession session, HttpServletResponse response){
        // 设置请求头输入为图片类型
        response.setContentType("image/jpg");
        // 设置不用缓存，防止验证码不刷新
        response.setHeader("Pargam","No-cache");
        response.setHeader("Cache-Control","no-cache");
        // 设置过期时间，永不失效
        response.setDateHeader("Expires",0);

        // 算术类型（长，宽，几个数的运算）
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(3);  // 几位数运算，默认是两位
        System.out.println(captcha.getArithmeticString()); // 获取运算公式 5x0+5=?
        System.out.println(captcha.text()); // 获取验证码结果

        // 图片英语字母数字类型
        //SpecCaptcha captcha = new SpecCaptcha(130, 48);

        // 英语字母数字gif类型的
        //GifCaptcha captcha = new GifCaptcha(130, 48,4);

        // 中文类型的
        //ChineseCaptcha captcha = new ChineseCaptcha(130, 48,3);

        // 中文gif类型
        //ChineseGifCaptcha captcha = new ChineseGifCaptcha(130, 48,4);

        // 将结果放入Redis，有效时长90s
        redisTemplate.opsForValue().set("captcha-result",captcha.text(),90, TimeUnit.SECONDS);
        log.info("captcha---->{}",captcha.text());
        // 输出验证码
        try {
            captcha.out(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
