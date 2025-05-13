package ru.gb.springdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springdemo.model.User;
import ru.gb.springdemo.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInDb = userRepository.findUserByName(username);

        Set<GrantedAuthority> authorities = userInDb.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());

        return org.springframework.security.core.userdetails.User
                .withUsername(userInDb.getName())
                .password(userInDb.getPassword())
                .authorities(authorities)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
