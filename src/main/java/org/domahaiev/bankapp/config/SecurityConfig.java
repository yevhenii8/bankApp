package org.domahaiev.bankapp.config;

import lombok.RequiredArgsConstructor;
import org.domahaiev.bankapp.security.AuthTokenFilter;
import org.domahaiev.bankapp.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthTokenFilter authTokenFilter;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // Отключение CSRF, если оно вам не нужно
                .authorizeHttpRequests(auth ->
                        auth
                                .anyRequest().authenticated() // Все запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/home", true) // URL для перенаправления после успешного логина
                        .permitAll() // Разрешить доступ к странице логина
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")) // Перенаправление после выхода
                .build();
    }
}
