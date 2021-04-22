package com.lisy.controller;

import com.lisy.entitys.User;
import com.lisy.service.IUserService;
import com.lisy.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author: lisy
 * @Date: 2021/4/20
 * @version: 1.0
 * @Description: ""
 */
@Api(tags = "IndexController")
@RestController
public class IndexController {

    @Resource
    IUserService userService;
    @ApiOperation(value = "IndexController")
    @GetMapping(value = "/system/cfg/{id}")
    public RespBean getUserInfo(@PathVariable Integer id){

        User byId = userService.getById(id);
        System.err.println(byId);
        return RespBean.success("成功!", byId.toString());

    }
}
