package com.lisy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lisy.entitys.Menu;
import com.lisy.utils.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据用户ID查询菜单列表
     * @return
     */
    List<Menu> getMenuByUserId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 根据用户ID查询按钮权限
     * @param
     * @return
     */
    List<Menu> findByMenuBtnUserId();
}
