package com.ctottene.application.usecase.auth.impl;

import com.ctottene.application.usecase.auth.LoginUseCase;
import com.ctottene.domain.gateway.UserRepository;
import com.ctottene.application.usecase.auth.dto.LoginInput;
import com.ctottene.application.usecase.auth.dto.LoginOutput;
import com.ctottene.domain.model.User;
import com.ctottene.infrastructure.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginUseCaseImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public LoginOutput execute(LoginInput input) {
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new LoginOutput(token, user.getRole().name());
    }
}
