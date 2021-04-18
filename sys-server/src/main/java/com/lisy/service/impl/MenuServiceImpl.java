package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.Menu;
import com.lisy.entitys.User;
import com.lisy.mapper.MenuMapper;
import com.lisy.service.IMenuService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    MenuMapper menuMapper;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据用户ID查询菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenuByUserId() {
        Integer userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        ValueOperations valueOperations = redisTemplate.opsForValue();

        // 从redis 获取menu
        List<Menu> menuList = (List<Menu>) valueOperations.get("menu_" + userId);
        // 获取的menuList为空 数据库查询
        if (Collections.isEmpty(menuList)) {
            menuList = menuMapper.getMenuByUserId(userId);
            valueOperations.set("menu_" + userId, menuList);
        }
        return menuList;
    }
    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return null;
    }
}
