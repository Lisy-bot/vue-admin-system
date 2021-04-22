package com.lisy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lisy.entitys.Menu;
import com.lisy.utils.RespBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户ID查询菜单列表
     * @return
     */
    List<Menu> getMenuByUserId(Integer userId);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    List<Menu> getAllByParentId(Integer pid);
}
