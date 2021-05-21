package com.lisy.controller.system;


import com.lisy.entitys.User;
import com.lisy.mapper.UserMapper;
import com.lisy.service.IUserService;
import com.lisy.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Resource
    IUserService userService;

    @ApiOperation(value = "管理员用户注册")
    @PostMapping("/add")
    public RespBean addUser(@RequestBody User user) {
        return userService.sysUserAdd(user);
    }


    @ApiOperation("根据用户ID查询当前用户")
    @GetMapping("/{id}")
    public RespBean getById(@PathVariable() Integer id) {
        return RespBean.success("成功!", userService.getById(id));
    }

    @ApiOperation("用户添加")
    @PostMapping
    public RespBean add(@Validated @RequestBody User user) {
        User byUserName = userService.getByUserName(user.getUsername());
        if(StringUtils.isEmpty(byUserName)){
            return RespBean.error("该用户已存在!");
        }
        return RespBean.success("添加成功!", userService.save(user));
    }

    @ApiOperation("用户更新")
    @PutMapping
    public RespBean update(@RequestBody User user) {
        return RespBean.success("更新成功!", userService.updateById(user));
    }
    @ApiOperation("用户更新")
    @DeleteMapping("/{id}")
    public RespBean update(@Validated Integer id) {
        return RespBean.success("删除成功!", userService.removeById(id));
    }

    @ApiOperation("用户更新")
    @DeleteMapping("/findSysUserAll")
    public RespBean findAll() {
        return RespBean.success("删除成功!", userService.listMaps());
    }

}
