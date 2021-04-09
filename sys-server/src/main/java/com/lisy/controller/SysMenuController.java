package com.lisy.controller;


import com.lisy.entitys.SysMenu;
import com.lisy.service.ISysMenuService;
import com.lisy.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/system")
public class SysMenuController {

    @Resource
    ISysMenuService sysMenuService;

    @ApiOperation(value = "通过用户ID菜单列表")
    public List<SysMenu> selectMenuPermsByUserId() {
        return sysMenuService.selectMenuPermsByUserId();
    }

}
