package com.jjj.cashlesstips.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jjj.cashlesstips.service.UserService;

import lombok.RequiredArgsConstructor;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<Long> {

    private final UserService userService;

    @Override
    public Optional<Long> getCurrentAuditor() {
       
        return userService.getCurrentUserId();
                            
    }
}
