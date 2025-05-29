package com.example.techpraktika.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/registration", "/webjars/**", "/img/**",
                                "/uploads/**", "/sw.js", "/js/**", "/css/**", "/login", "/error"
                        ).permitAll()
                        .requestMatchers("/brigades/**","/constructionSite/**","brigadeWorkers/**","clients/**","defects/**","materials/**","stages/**","supplier/**","/guide").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/brigades")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("A6OzwHU19WzPexMsBAfC")
                        .tokenValiditySeconds(86400)
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .logoutUrl("/logout")
                        .permitAll()
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

}
