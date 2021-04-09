package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.SysMenu;
import com.lisy.entitys.SysUser;
import com.lisy.mapper.SysMenuMapper;
import com.lisy.service.ISysMenuService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 根据用户ID获取菜单列表
     *
     * @return
     */
    @Override
    public List<SysMenu> selectMenuPermsByUserId() {
        return sysMenuMapper.selectMenuPermsByUserId(((SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
    }
}
