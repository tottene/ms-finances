package com.ctottene.infrastructure.security;

import com.ctottene.domain.gateway.UserRepository;
import com.ctottene.domain.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticatedUser authenticatedUser;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository, AuthenticatedUser authenticatedUser) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (jwtService.isTokenValid(token, user)) {
                    authenticatedUser.setAuthenticated(
                            user.getId(),
                            user.getTenantId(),
                            user.getEmail(),
                            user.getPassword(),
                            user.getUserTimeZone(),
                            Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))

                    );

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            authenticatedUser, null, authenticatedUser.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("Token is valid. Authenticating user: {}", email);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info("User authenticated.");
                    log.info("Authorities: {}", authenticatedUser.getAuthorities());


                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
