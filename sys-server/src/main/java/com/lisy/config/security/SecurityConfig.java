package com.lisy.config.security;
import com.lisy.config.security.component.*;
import com.lisy.entitys.User;
import com.lisy.service.IRoleService;
import com.lisy.service.IUserService;
import com.lisy.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private CustomFilter customFilter;
    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf()
               .disable()
               // ??????token ?????????sesion
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeRequests()
               // ??????????????????
               // .antMatchers("/login", "logout")
               // .permitAll()
               // ???????????????????????????
               .anyRequest()
               .authenticated()
               // ??????????????????
               .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                   @Override
                   public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                       object.setAccessDecisionManager(customUrlDecisionManager);
                       object.setSecurityMetadataSource(customFilter);
                       return object;
                   }
               })
               .and()
               // ????????????
               .headers()
               .cacheControl();
       // ??????jwt?????????????????????
       http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
       // ????????????????????????????????????????????????
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
            User user = userService.getByUserName(userName);
            if (user != null) {
                user.setRoles(roleService.getRoles(user.getId()));
                return user;
            }
            // throw new UsernameNotFoundException("????????????????????????!");
            return null;
        };
    }

    /**
     * ????????????
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/JX_CUCC/jsp/jsp/pkdata/checkOrder.jsp",
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
