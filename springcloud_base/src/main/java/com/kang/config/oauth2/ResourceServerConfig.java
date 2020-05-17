package com.kang.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * <p>Title: ResourceServerConfig</p>
 * <p>Description: 资源服务器<p>
 * @author ChaoKang
 * @date 2020年5月10日
 */ 
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
//        .antMatchers("/user/**").permitAll()
        .antMatchers("/**").authenticated()
        .and().headers().frameOptions().disable();

}
}