package com.sky.order.server.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

// 配置了@EnableResourceServer的资源服务器，所有发往此服务器的http请求，它都会从头里找Token，
// 找不到token就不让过
@Configuration
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("order-server");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @EnableResourceServer找不到token就不让http请求过是这方法的父方法控制的
//        http.authorizeRequests().anyRequest().authenticated();
        // 在有些情况下需要做一些权限控制，哪些请求需要验令牌，哪些请求不需要验令牌，当需要做控制的时候，可以覆盖此方法重写配置
// 除了haha不需要验令牌，气它都需要
        http.authorizeRequests().antMatchers("/haha").permitAll()
                .anyRequest().authenticated();
    }
}
