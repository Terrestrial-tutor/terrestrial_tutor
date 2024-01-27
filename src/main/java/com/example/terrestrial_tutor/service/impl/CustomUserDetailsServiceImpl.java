package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.User;
import com.example.terrestrial_tutor.repository.UserRepository;
import com.example.terrestrial_tutor.service.CustomUserDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username:" + username);
        }

        return build(user);
    }

    public User loadUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public static User build(User user) {
        GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().name());
        return new User (user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                authorities);
    }
}
