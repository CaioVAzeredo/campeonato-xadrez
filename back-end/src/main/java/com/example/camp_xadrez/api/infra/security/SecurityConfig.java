package com.example.camp_xadrez.api.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desativa CSRF (opcional p/ APIs REST)
                .authorizeHttpRequests(auth -> auth
                        // libera swagger
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**"
                        ).permitAll()
                        // libera health sem login
                        .requestMatchers("/health").permitAll()
                        // qualquer outra requisição exige login
                        .anyRequest().authenticated()
                )
                // habilita Basic Auth
                .httpBasic(basic -> {});

        return http.build();
    }
}