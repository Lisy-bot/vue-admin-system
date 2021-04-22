package com.lisy.controller.system;

import com.lisy.entitys.SysUserLoginParam;
import com.lisy.entitys.User;
import com.lisy.service.IRoleService;
import com.lisy.service.IUserService;
import com.lisy.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
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
    private IUserService userService;
    @Resource
    private IRoleService roleService;

    @ApiOperation(value = "登录成功返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody SysUserLoginParam sysUserLoginParam, HttpServletRequest request) {
        return userService.login(sysUserLoginParam.getUsername(), sysUserLoginParam.getPassword(), sysUserLoginParam.getCaptcha(), request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/getUser/info")
    public User getUserInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        user.setPassword(null);
        user.setRoles(roleService.getRoles(user.getId()));
        return user;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/lagout")
    public RespBean lagout() {
        return RespBean.success("注销成功!");
    }

}
