package com.habitHatch.security;

import com.habitHatch.UserMgmt.UserMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    @Lazy
    private  UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(register -> {
                    register.requestMatchers("/login").permitAll() // Allow login endpoint
                            .requestMatchers("/v1/user/**").hasRole("USER")
                            .anyRequest().authenticated();
                })
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .formLogin(form -> form.disable()) // Disable form login
                .build();
    }

//
//    @Bean
//    public UserDetailsService userLogin() {
//        UserDetails adminUser= User.builder()
//                .username("user1")
//                .password("$2a$12$8b8BrCCUKamDjPGQPRQroOOXh8WhE8wpDZcvHJjakCUTtu2Q2u4Sm")
//                .password(encode().encode("pass@123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(adminUser);
//    }
    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encode());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();}

}
