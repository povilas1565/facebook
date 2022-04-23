package com.example.facebook.service;

import com.example.facebook.entity.User;
import com.example.facebook.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ConfigUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ConfigUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //1. Ищем пользователя в БД
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found:" + username));
        return initUser(user);
    }

    //2. Конвертация в Spring Security
    public static User initUser(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(ERole -> new SimpleGrantedAuthority(ERole.name()))
                .collect(Collectors.toList());
        //Наделяем нашего пользователя полномочиями
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), authorities);
    }


    public User loadUserByUserId(Long id) {

        return userRepository.findUserById(id).orElse(null);
    }
}

