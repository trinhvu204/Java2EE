package com.example.Lap05.config;


import com.example.Lap05.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AccountService accountService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()

                        // USER và ADMIN được xem
                        .requestMatchers("/products").hasAnyRole("USER","ADMIN")

                        // chỉ ADMIN được thêm sửa xoá
                        .requestMatchers("/products/add").hasRole("ADMIN")
                        .requestMatchers("/products/edit/**").hasRole("ADMIN")
                        .requestMatchers("/products/delete/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/products", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }
}
