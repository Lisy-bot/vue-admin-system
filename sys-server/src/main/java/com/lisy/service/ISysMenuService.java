package com.lisy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lisy.entitys.SysMenu;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据用户id获取菜单列表
     * @return
     */

    List<SysMenu> selectMenuPermsByUserId();
}
