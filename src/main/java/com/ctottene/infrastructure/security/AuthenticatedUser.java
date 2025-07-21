package com.ctottene.infrastructure.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.UUID;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthenticatedUser implements UserDetails {

    private UUID id;
    private UUID tenantId;
    private String email;
    private String password;
    private String timezone;
    private Collection<? extends GrantedAuthority> authorities;

    public void setAuthenticated(UUID id, UUID tenantId, String email, String password, String timezone, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.tenantId = tenantId;
        this.email = email;
        this.password = password;
        this.timezone = timezone;
        this.authorities = authorities;
    }

    public UUID getId() { return id; }
    public UUID getTenantId() { return tenantId; }
    public String getTimezone() { return timezone; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
