package com.energizeglobal.bank.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private final CustomBasicAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomPasswordEncoder customPasswordEncoder;

    public CustomWebSecurityConfigurerAdapter(CustomBasicAuthenticationEntryPoint authenticationEntryPoint,
                                              CustomPasswordEncoder customPasswordEncoder) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("EGS_USER")
                .password(customPasswordEncoder.encode("EGS_PASS"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()//.realmName("EGS")
                .authenticationEntryPoint(authenticationEntryPoint);
    }
}
