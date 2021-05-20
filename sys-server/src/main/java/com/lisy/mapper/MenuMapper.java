package com.lisy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lisy.entitys.Menu;
import com.lisy.utils.RespBean;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
    /**
     * 根据用户ID查询菜单列表
     * @param userId
     * @return
     */
    List<Menu> findByMenuUserId(@Param("userId") Integer userId, @Param("type") Integer type);
}
