package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lisy.entitys.SysUserPost;
import com.lisy.mapper.SysUserPostMapper;
import com.lisy.service.ISysUserPostService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {

}
