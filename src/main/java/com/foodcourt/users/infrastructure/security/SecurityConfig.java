package com.foodcourt.users.infrastructure.security;

import com.foodcourt.users.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers(PathsConstants.PATHS_ALL_ALLOW).permitAll()
                    .requestMatchers(PathsConstants.PATHS_ADMIN).hasAuthority(UserRole.ADMIN.name())
                    .requestMatchers(PathsConstants.PATHS_OWNER).hasAuthority(UserRole.OWNER.name())
                    .requestMatchers(PathsConstants.PATHS_EMPLOYEE).hasAuthority(UserRole.EMPLOYEE.name())
                    .anyRequest()
                    .authenticated()
            ).sessionManagement(sessionManageConfig ->
                sessionManageConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
