package com.jjj.cashlesstips.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.jjj.cashlesstips.service.UserService;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    AuditorAware<Long> auditorProvider(@Autowired UserService userService) {
        return new AuditorAwareImpl(userService);
    }
    
}
