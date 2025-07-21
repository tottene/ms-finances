package com.ctottene.app.controller;

import com.ctottene.application.usecase.LoginUseCase;
import com.ctottene.application.usecase.dto.LoginInput;
import com.ctottene.application.usecase.dto.LoginOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> login(@RequestBody LoginInput request) {
        return ResponseEntity.ok(loginUseCase.execute(request));
    }
}