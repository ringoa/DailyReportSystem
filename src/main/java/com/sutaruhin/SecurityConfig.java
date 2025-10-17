package com.sutaruhin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
            .loginProcessingUrl("/login")
            .loginPage("/login")
            .successHandler(customLoginSuccessHandler)
            .failureUrl("/login?error")
            .permitAll()
        ).logout(logout -> logout
            .logoutSuccessUrl("/login")
        ).authorizeHttpRequests(auth -> auth
    		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
    	    .requestMatchers("/login").permitAll()
    	    .requestMatchers("/employee/**").hasAuthority("管理者")
    	    .anyRequest().authenticated()
        );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
