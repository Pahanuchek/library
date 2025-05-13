package ru.gb.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomFilterChain {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(c -> {
            c.requestMatchers("/ui/issues/**").hasRole("admin");
            c.requestMatchers("/ui/readers/**").hasRole("user");
            c.requestMatchers("/ui/books").authenticated();
            c.anyRequest().permitAll();
        }).httpBasic(Customizer.withDefaults())
                .build();
    }
}
