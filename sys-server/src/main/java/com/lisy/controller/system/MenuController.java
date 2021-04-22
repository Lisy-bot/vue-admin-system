package com.lisy.controller.system;


import com.lisy.entitys.Menu;
import com.lisy.service.IMenuService;
import com.lisy.utils.RespBean;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
@RestController
@RequestMapping("/system/config")
public class MenuController {
    @Autowired
    private IMenuService menuService;


    @ApiModelProperty(value = "通过用户ID查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByUserId(){
       return menuService.getMenuByUserId();
    }

}
