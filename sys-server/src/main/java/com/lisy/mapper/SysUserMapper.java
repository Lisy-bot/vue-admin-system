package com.lisy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lisy.entitys.SysMenu;
import com.lisy.entitys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser getBySysUser(String userName);
}
