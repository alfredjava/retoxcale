package com.xcale.ecommerce.infrastructure.config;

import com.xcale.ecommerce.infrastructure.security.jwt.JwtAuthenticationFilter;
import org.springframework.http.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(new AntPathRequestMatcher("/auth/**"),
                                        new AntPathRequestMatcher("/v3/api-docs", HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/swagger-ui.html", HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/webjars/**", HttpMethod.GET.name()),
                                        new AntPathRequestMatcher("/v3/**", HttpMethod.GET.name())
                                )
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }


    /*

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(new AntPathRequestMatcher("/openapi/openapi.yml")).permitAll()
                        .anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }

     */

}
