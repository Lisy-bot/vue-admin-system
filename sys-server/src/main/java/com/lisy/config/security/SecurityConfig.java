package com.lisy.config.security;

import com.lisy.entitys.SysUser;
import com.lisy.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.annotation.Resource;

/**
 * @Author: lisy
 * @Date: 2021/4/7
 * @version: 1.0
 * @Description: ""
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private ISysUserService SysUserService;
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf()
               .disable()
               // 基于token 不需要sesion
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeRequests()
               // 允许登录访问
               // .antMatchers("/login", "logout")
               // .permitAll()
               // 所有请求都需要认证
               .anyRequest()
               .authenticated()
               .and()
               // 禁用缓存
               .headers()
               .cacheControl();
       // 添加jwt登录授权过滤器
       http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
       // 添加自定义未授权和未登录结果返回
       http.exceptionHandling()
               .accessDeniedHandler(restAccessDeniedHandler)
               .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncode());
    }
    @Bean
    public PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return userName ->{
            SysUser sysUser = SysUserService.getBySysUser(userName);
            if (sysUser != null) {
                return sysUser;
            }
            return null;
        };
    }

    /**
     * 允许登录
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/captcha",
                "/logout",
                "/css/**",
                "/js/**",
                "/images/**",
                "favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**");
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
}
