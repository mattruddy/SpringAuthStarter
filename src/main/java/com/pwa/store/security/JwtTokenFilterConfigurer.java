package com.pwa.store.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;
    private UserIdentity userIdentity;

    public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProvider, UserIdentity userIdentity) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userIdentity = userIdentity;
    }

    @Override
    public void configure(HttpSecurity http) {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider, userIdentity);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
