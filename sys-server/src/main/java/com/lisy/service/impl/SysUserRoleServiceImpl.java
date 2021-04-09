package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.SysUserRole;
import com.lisy.mapper.SysUserRoleMapper;
import com.lisy.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
