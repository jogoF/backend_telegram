package com.jogo.tochat.services;

import com.jogo.tochat.dtos.AuthenticationRequest;
import com.jogo.tochat.dtos.RegisterRequest;
import com.jogo.tochat.entities.Role;
import com.jogo.tochat.entities.User;
import com.jogo.tochat.exceptions.AlreadyExistsException;
import com.jogo.tochat.repositories.AuthenticationResponse;
import com.jogo.tochat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws AlreadyExistsException {
        Optional<User> existUserEmail = this.userRepository.findUserByEmail(request.getEmail());
        if (existUserEmail.isEmpty()) {
            User user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(encoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            this.userRepository.save(user);
            var jwtToken = jwtService.generaToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else {
            throw new AlreadyExistsException("this email already exist:" + request.getEmail());
        }
    }

    public AuthenticationResponse login(AuthenticationRequest request)  {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generaToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
