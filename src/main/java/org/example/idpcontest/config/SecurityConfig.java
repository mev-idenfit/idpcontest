package org.example.idpcontest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Author: Mevlüt Beder
 * Company: Idenfit
 * Date: 2025-03-26 15:00:38
 * Changelog:
 * - 2025-03-26 15:00:38: Initial creation
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/**", "/logout/**","/admin/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .saml2Login(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout/saml2/slo")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }


}
