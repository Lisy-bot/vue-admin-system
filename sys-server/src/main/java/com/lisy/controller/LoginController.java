package com.lisy.controller;

import com.lisy.entitys.SysUser;
import com.lisy.entitys.SysUserLoginParam;
import com.lisy.service.ISysUserService;
import com.lisy.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Author: lisy
 * @Date: 2021/4/1
 * @version: 1.0
 * @Description: "登录"
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {
    @Autowired
    private ISysUserService sysUserService;
    @ApiOperation(value = "登录成功返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody SysUserLoginParam sysUserLoginParam, HttpServletRequest request){
        return sysUserService.login(sysUserLoginParam.getUsername(), sysUserLoginParam.getPassword(), sysUserLoginParam.getCaptcha(), request);
    }
    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/getUser/info")
    public SysUser getUserInfo(Principal principal){
        if(principal == null){
            return null;
        }
        String userName = principal.getName();
        SysUser sysUser = sysUserService.getBySysUser(userName);
        sysUser.setPassword(null);
        return sysUser;
    }
    @ApiOperation(value = "退出登录")
    @PostMapping("/lagout")
    public RespBean lagout(){
        return RespBean.success("注销成功!");
    }

}
