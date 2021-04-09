package com.lisy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Constants;
import com.lisy.config.security.JwtTokenUtil;
import com.lisy.entitys.SysMenu;
import com.lisy.entitys.SysUser;
import com.lisy.mapper.SysUserMapper;
import com.lisy.service.ISysUserService;
import com.lisy.utils.RespBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lisy
 * @since 2021-04-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHeader;

    /**
     * 登录后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String captcha, HttpServletRequest request) {
        String captchaCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isEmpty(captcha) || captchaCode.equalsIgnoreCase(captcha)) {
            return RespBean.error("验证码输入错误,请重新输入!");
        }
        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户或密码错误");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("该账户被禁用,请联系管理员!");
        }
        // 更新security登录信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成token
        String token = jwtTokenUtil.generatorToken(userDetails);
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHeader", tokenHeader);
        return RespBean.success("登录成功!", tokenMap);
    }

    /**
     * 根据用户名查询可用用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public SysUser getBySysUser(String userName) {
        return sysUserMapper.getBySysUser(userName);
    }

    /**
     * 新用户注册
     *
     * @param sysUser
     * @return
     */
    @Override
    public RespBean sysUserAdd(SysUser sysUser) {
        SysUser bySysUser = getBySysUser(sysUser.getUsername());
        if (bySysUser != null) {
            return RespBean.error("已存在该用户");
        }
        if (bySysUser != null && save(sysUser)) {
            return RespBean.success("注册成功!", sysUser);
        }
        return RespBean.error("注册失败!");
    }


}
