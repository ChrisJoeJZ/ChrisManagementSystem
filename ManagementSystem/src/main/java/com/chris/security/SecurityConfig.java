package com.chris.security;

import com.chris.security.handler.LogOutSuccessHandler;
import com.chris.security.handler.LoginErrorHandler;
import com.chris.security.handler.LoginSuccessHandler;
import com.chris.security.handler.PermissionValidErrorHandler;
import com.chris.service.AdminService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private AdminService adminService;

    @Resource
    private PermissionAuthority permissionAuthority;


    @Resource
    private PermissionValid permissionValid;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;


    @Resource
    private LoginErrorHandler loginErrorHandler;

    @Resource
    private PermissionValidErrorHandler permissionValidErrorHandler;

    @Resource
    private LogOutSuccessHandler logOutSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置登录验证的方式
        auth.userDetailsService(adminService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置放行路径
        web.ignoring().antMatchers("/login","/index/login","/img/**","/js/**","/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置security
        http.headers().frameOptions().disable()     // 允许iframe
                .and()
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(permissionAuthority);      // 授权对象
                        object.setAccessDecisionManager(permissionValid);           // 权限验证的对象
                        return object;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/index/login")                                  // 登录的表单页面
                .loginProcessingUrl("/index/loginDo")                       // 登录表单的处理页面
                .usernameParameter("admin_name")                            // 账户名称
                .passwordParameter("admin_pass")                            // 账户密码
                .failureHandler(loginErrorHandler)                          // 登录处理器
                .successHandler(loginSuccessHandler)                        // 登录成功的处理器
                .permitAll()
                .and()
                .logout()                           // 退出
                .logoutUrl("/index/logout")         // 安全退出页面
                .logoutSuccessHandler(logOutSuccessHandler)             // 退出成功处理器
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(permissionValidErrorHandler);         // 权限验证失败的处理器
    }
}
