package com.jogo.tochat.controllers;

import com.jogo.tochat.dtos.AuthenticationRequest;
import com.jogo.tochat.exceptions.AlreadyExistsException;
import com.jogo.tochat.repositories.AuthenticationResponse;
import com.jogo.tochat.services.AuthenticationService;
import com.jogo.tochat.dtos.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public AuthenticationResponse register (
          @Valid @RequestBody RegisterRequest request
    ) throws AlreadyExistsException {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return authService.login(request);
    }
}
