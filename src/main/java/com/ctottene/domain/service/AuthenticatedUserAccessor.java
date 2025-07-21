package com.ctottene.domain.service;

import java.util.UUID;

public interface AuthenticatedUserAccessor {
    UUID getUserId();
    UUID getTenantId();
}
