package com.lisy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lisy.entitys.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
public interface UserMapper extends BaseMapper<User> {
    User getByUserName(String userName);
}
