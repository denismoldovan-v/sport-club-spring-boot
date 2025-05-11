package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключаем CSRF для упрощения на этапе разработки
                .csrf(csrf -> csrf.disable())

                // Настраиваем доступ к страницам
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/login/**", "/register/**", "/perform_login/**", "/resources/**", "/wydarzenia/**", "/error", "/css/**", "/js/**", "/images/**", "/api/wydarzeniaSportowe/**").permitAll() // Доступ ко всем этим URL
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/zawodnik/**").hasRole("ZAWODNIK")
                        .anyRequest().authenticated()) // Остальное требует авторизации

                // Настраиваем стандартный login page
                .formLogin(form -> form
                        .loginPage("/login") // Указываем кастомный URL для логина
                        .loginProcessingUrl("/perform_login") // Обрабатываем логин на этом URL
                        .defaultSuccessUrl("/home", true) // Перенаправляем на домашнюю страницу после успешного входа
                        .failureUrl("/login?error=true") // Если ошибка логина
                        .permitAll()) // Разрешаем всем доступ к странице логина

                // Добавляем logout для завершения сессии
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для logout
                        .logoutSuccessUrl("/") // Перенаправление после logout
                        .permitAll());

        return http.build();
    }
}
