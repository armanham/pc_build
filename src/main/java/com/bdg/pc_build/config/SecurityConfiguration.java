package com.bdg.pc_build.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                        "/api/v1/auth/**",
                                        "/api/v1/filter/**",
                                        "/api/v1/product/case",
                                        "/api/v1/product/cooler",
                                        "/api/v1/product/cpu-cooler",
                                        "/api/v1/product/cpu",
                                        "/api/v1/product/gpu",
                                        "/api/v1/product/internal-hard-drive",
                                        "/api/v1/product/motherboard",
                                        "/api/v1/product/power-supply",
                                        "/api/v1/product/ram",
                                        "/api/v1/product/external-hard-drive",
                                        "/api/v1/product/monitor",
                                        "/api/v1/product/headset",
                                        "/api/v1/product/keyboard",
                                        "/api/v1/product/mouse",
                                        "/api/v1/product/speaker",
                                        "/api/v1/pc-builder/check",
                                        "/api/v1/pc-builder/add",
                                        "/api/v1/pc-builder/remove",
                                        "/api/v1/pc-builder/get",
                                        "/api/v1/pc-builder/clear",
                                        "/api/v1/pc-builder/complete-built-computers",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()
                        )
                );

        return http.build();
    }
}