package com.pdx.radar.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 派大星
 * @Date: 2023/02/13 2023/2/13
 * @Description: 用户登录实体类
 */
@Data
@ApiModel(value = "Login对象",description = "")
public class LoginVo {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "验证码结果")
    private String captcha;
}
