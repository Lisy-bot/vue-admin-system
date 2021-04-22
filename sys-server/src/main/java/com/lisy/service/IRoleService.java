package com.lisy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lisy.entitys.Role;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lisy
 * @since 2021-04-17
 */
public interface IRoleService extends IService<Role> {
    /**
     * 根据用户ID获取角色列表
     * @return
     */
    List<Role> getRoles(Integer userId);
}
