package com.lisy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lisy.entitys.Role;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoles(Integer userId);
}
