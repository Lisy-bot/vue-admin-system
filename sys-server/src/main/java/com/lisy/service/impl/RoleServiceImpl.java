package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.Role;
import com.lisy.mapper.RoleMapper;
import com.lisy.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    RoleMapper roleMapper;

    /**
     * 根据用户IDID查询角色列表
     * @param userId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer userId) {
        return roleMapper.getRoles(userId);
    }
}
