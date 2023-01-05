package com.jjj.cashlesstips.security;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.jjj.cashlesstips.security.jwt.JwtConfig;
import com.jjj.cashlesstips.security.jwt.JwtTokenVerifier;
import com.jjj.cashlesstips.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration{
    
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    // @Bean
    // public DaoAuthenticationProvider daoAuthenticationProvider() {
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setPasswordEncoder(passwordEncoder);
    //     provider.setUserDetailsService(userDetailService);
    //     return provider;
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {       
        http
            .cors()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager, jwtConfig, secretKey))
            .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)
            .authorizeHttpRequests((authz) -> authz
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/order/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
