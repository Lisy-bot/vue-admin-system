package com.lisy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lisy.entitys.SysMenu;
import com.lisy.entitys.SysUser;
import com.lisy.utils.RespBean;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 登录后返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password, String captcha, HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    SysUser getBySysUser(String userName);

    /**
     * 用户添加
     * @param sysUser
     * @return
     */
    RespBean sysUserAdd(SysUser sysUser);


}
