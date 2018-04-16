package com.xw.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()                        //表单登陆
                .loginPage("/login/authentication")         //指定需要登录时发送用户的URL，根据需要重定向请求，需要自己写对应的请求路径
                .loginProcessingUrl("/login/form")          //指定验证凭据的URL，和表单路径一样
//                 .passwordParameter("password")//form表单用户名参数名
//                .usernameParameter("username") //form表单密码参数名
//                .successForwardUrl("/success.html")  //登录成功跳转地址
//                .failureForwardUrl("/error.html") //登录失败跳转地址
                .and()
                .authorizeRequests()
                .antMatchers("/login.html", "/login/authentication")
                .permitAll()                                //上面匹配的请求允许访问
                .anyRequest()
                .authenticated()                            //其他请求需要认证才能访问
                .and()
                .csrf().disable();
    }
}
