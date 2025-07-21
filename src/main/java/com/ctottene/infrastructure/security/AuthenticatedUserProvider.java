package com.ctottene.infrastructure.security;

import com.ctottene.domain.service.AuthenticatedUserAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticatedUserProvider implements AuthenticatedUserAccessor {

    @Override
    public UUID getUserId() {
        var user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    @Override
    public UUID getTenantId() {
        var user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getTenantId();
    }
}
