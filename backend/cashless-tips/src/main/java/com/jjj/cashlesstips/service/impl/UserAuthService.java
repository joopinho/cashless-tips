package com.jjj.cashlesstips.service.impl;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjj.cashlesstips.repository.UserRepository;
import com.jjj.cashlesstips.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        return getUserPrincipal(username);
    }

    public UserPrincipal getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserPrincipal){
            return (UserPrincipal)principal;
        }

        return getUserPrincipal(principal.toString());
    }

    private UserPrincipal getUserPrincipal(String usernname){
        return userRepository.findByUsername(usernname)
                .map(user -> new UserPrincipal(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
