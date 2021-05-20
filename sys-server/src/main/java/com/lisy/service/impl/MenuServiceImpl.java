
package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.Menu;
import com.lisy.entitys.User;
import com.lisy.mapper.MenuMapper;
import com.lisy.service.IMenuService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.reflect.misc.ConstructorUtil;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return
     */
    @Override
    public List<Menu> getMenuByUserId() {
        // 获取用户ID
        Integer userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 从redis 获取menu
        List<Menu> menuList = (List<Menu>) valueOperations.get("menu_" + userId);
        // 获取的menuList为空 数据库查询
        if (Collections.isEmpty(menuList)) {
            menuList = menuMapper.findByMenuUserId(userId, null);
            final List<Menu> menus = menuList.stream().filter(l -> l.getType().equals(1))
                    .collect(Collectors.toList());
            //支持多级菜单
            List<Menu> firstLevel = menus.stream().filter(p -> p.getParentId().equals(0)).collect(Collectors.toList());
            firstLevel.parallelStream().forEach(m -> {
                setChild(m, menus);
            });
            valueOperations.set("menu_" + userId, firstLevel);
            return firstLevel;
        }
        return menuList;
    }
    /**
     * 设置子元素
     * @param m
     * @param menus
     */
    private void setChild(Menu m, List<Menu> menus) {
        List<Menu> child = menus.parallelStream().filter(a -> a.getParentId().equals(m.getId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(child)) {
            m.setChildren(null);
        } else {
            m.setChildren(child);
        }
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, menus);
            });
        }
    }
    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    @Override
    public List<Menu> findByMenuBtnUserId() {
        // 获取用户ID
        Integer userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 从redis 获取menu
        ArrayList btnlist = (ArrayList) valueOperations.get("menuBtn_" + userId);
        if(Collections.isEmpty(btnlist)){
            List<Menu> menuBtns = menuMapper.findByMenuUserId(userId, 2);
            btnlist = new ArrayList();
            for(Menu url : menuBtns) {
                btnlist.add(url.getUrl());
            }
            valueOperations.set("menuBtn_" + userId, btnlist);
        }
        return btnlist;
    }
}
