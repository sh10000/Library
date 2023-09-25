package com.crud.config;

import com.crud.handle.MyAuthenticationFailureHandle;
import com.crud.handle.MyAuthenticationSuccessHandle;
import com.crud.handle.MySuccessDeniedHandler;
import com.crud.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MySuccessDeniedHandler mySuccessDeniedHandler;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/login").loginPage("/pages/login.html")
                //必须为post请求
//                .successForwardUrl("/toMain")
                .successHandler(new MyAuthenticationSuccessHandle("/pages/books.html"))
                .failureForwardUrl("/toError");
//                .failureHandler(new MyAuthenticationFailureHandle("/toError"));
//        关闭防火墙
        http.csrf().disable();

        //所有请求都必须认证
        http.authorizeHttpRequests()
                .antMatchers("/pages/error.html").permitAll()
                .antMatchers("/pages/login.html").permitAll()
                .antMatchers("/js/**","/css/**","/plugins/**").permitAll()
                .antMatchers("/api/").permitAll()
                .antMatchers("/upload").permitAll()
                //权限认定
//                .antMatchers("/pages/无权限.html").hasAuthority("p1")
//                .antMatchers("/pages/无权限.html").hasAnyAuthority("admin","admIn")
//                .antMatchers("/pages/无权限.html").hasRole("p1")
                .anyRequest().authenticated();
//        .anyRequest().access("@MyServiceImpl.hasPermission(request,authentication)");
        //权限不足
        http.exceptionHandling().accessDeniedHandler(mySuccessDeniedHandler);
        http.rememberMe()
                .tokenValiditySeconds(60*60*12*7)
//                .rememberMeParameter()
                .userDetailsService(userService)
                //持久层对象
                .tokenRepository(persistentTokenRepository);
        http.logout()
                //退出登录跳转界面
                .logoutSuccessUrl("/pages/login.html");
    }



}
