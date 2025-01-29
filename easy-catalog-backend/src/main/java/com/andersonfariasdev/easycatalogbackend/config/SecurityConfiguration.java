package com.andersonfariasdev.easycatalogbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // Configura o acesso
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/h2-console/**").permitAll()  // Permite o acesso ao H2 Console sem autenticação
                        .anyRequest().authenticated()  // Requer autenticação para qualquer outra requisição
                )
                // Desabilita CSRF (necessário para o H2 Console)
                .csrf(AbstractHttpConfigurer::disable)
                // Desabilita o uso de frames (necessário para o H2 Console funcionar dentro de um iframe)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                );
        return http.build();
    }
}
