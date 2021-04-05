package com.fastcampus.java.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Autowired
    public Optional<String> getCurrentAuditor() {
        return Optional.of("AdminServer");
    }
}
