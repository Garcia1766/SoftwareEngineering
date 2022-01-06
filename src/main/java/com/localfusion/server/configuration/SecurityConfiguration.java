package com.localfusion.server.configuration;

import com.localfusion.server.constant.UserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String TEST_NAME = "admin";
    private static final String TEST_PASS = "admin";

    public static final String BASIC_AUTH_CODE = Base64.getEncoder().encodeToString((TEST_NAME + ":" + TEST_PASS).getBytes());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // 而其他的请求都需要认证
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser(TEST_NAME).password(new BCryptPasswordEncoder().encode(TEST_PASS)).roles(UserRole.ADMIN);
    }
}
