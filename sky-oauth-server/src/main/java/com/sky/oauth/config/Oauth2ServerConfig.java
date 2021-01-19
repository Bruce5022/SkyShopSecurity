package com.sky.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配用来检查token的服务的访问规则，这里写一个权限表达式
    // Spring security提供了一组权限表达式，可以根据权限表达式组装出各种各样的复杂的权限控制
    // 这里的配置：来checkToken 的请求，一定是经过身份认证的，也就是你要么是orderApp密码是123456，要么是orderService密码是123456，
    // 必须带着你的信息来checkTokne，我才给你验，随便发一个让验我是不验的
        security.checkTokenAccess("isAuthenticated()");
}

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*// 配置哪些应用会来请求令牌,这里是配置了一个订单应用
        clients
                .inMemory()
                .withClient("orderApp") // 相当于应用的用户名
                .secret(passwordEncoder.encode("123456"))// 相当于应用的密码
                .scopes("read", "write")// 可用来做ACL的权限控制，这里配置的
                // 是orderApp可以获取到的所有权限的集合,可以获取一部分权限，但不能访问权限外的权限,这里的权限是针对资源服务器的
                // 这里的scope是访问资源服务器有哪些权限可做什么
                .accessTokenValiditySeconds(5 * 60)// 发出去的令牌多久失效
                .resourceIds("order-server")// 这里配置的是代表资源服务器的id，我发给orderApp的token，可以访问哪些资源服务器
                .authorizedGrantTypes("password")// 共四种授权类型，这里配置的是给orderApp用哪种方式授权

                .and()

                .withClient("orderService")
                .secret(passwordEncoder.encode("123456"))
                .scopes("read")
                .accessTokenValiditySeconds(5 * 60)
                .resourceIds("order-server")
                .authorizedGrantTypes("password");*/
        clients.jdbc(dataSource);


    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置认证管理器，用来校验传来的用户信息是否合法
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }
}
