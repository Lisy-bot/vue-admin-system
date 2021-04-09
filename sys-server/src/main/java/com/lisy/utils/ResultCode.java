package com.lisy.utils;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: lisy
 * @Date: 2021/4/8
 * @version: 1.0
 * @Description: ""
 */
public class ResultCode {
    @ApiModelProperty(value = "请求要求用户的身份认证")
    public static final Integer unauthorized = 401;

    @ApiModelProperty(value = "服务器理解请求客户端的请求，但是拒绝执行此请求")
    public static final Integer forbidden = 403;

    @ApiModelProperty(value = "服务器内部错误，无法完成请求")
    public static final Integer internalServerError= 500;
}
