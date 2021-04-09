package com.lisy.controller;


import com.lisy.entitys.SysUser;
import com.lisy.service.ISysUserService;
import com.lisy.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Resource
    ISysUserService sysUserService;

    @ApiOperation(value = "管理员用户注册")
    @PostMapping("/add")
    public RespBean addUser(@RequestBody SysUser sysUser){
       return sysUserService.sysUserAdd(sysUser);
    }
}
