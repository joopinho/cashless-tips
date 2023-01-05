package com.jjj.cashlesstips.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjj.cashlesstips.dao.entity.User;
import com.jjj.cashlesstips.repository.UserRepository;
import com.jjj.cashlesstips.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        String username = user.getUsername();
        userRepository.findByUsername(username).ifPresent(
            value -> {
                        throw new IllegalArgumentException("User with username: " 
                                                            + username + "  already exists");
            });
        
        return userRepository.save(user);

    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id: " + id + " is not found")); 
    }

    @Override
    public Optional<Long> getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null){
            return Optional.ofNullable(null);
        }        
        return userRepository.findByUsername(auth.getName()).map(user -> user.getId());
    }
    
}
