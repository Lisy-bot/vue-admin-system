package com.lisy.controller;


import com.lisy.entitys.User;
import com.lisy.service.IUserService;
import com.lisy.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService UserService;

    @ApiOperation(value = "管理员用户注册")
    @PostMapping("/add")
    public RespBean addUser(@RequestBody User user){
        return UserService.sysUserAdd(user);
    }
}
