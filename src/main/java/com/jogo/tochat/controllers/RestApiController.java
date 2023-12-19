package com.jogo.tochat.controllers;

import com.jogo.tochat.entities.User;
import com.jogo.tochat.exceptions.NotFoundException;
import com.jogo.tochat.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestApiController {

    private final UserService userService;

    @GetMapping("/find/{id}")
    public Optional<User> getUserById(@PathVariable int id ) throws NotFoundException {
        return this.userService.getUserById(id);
    }
}
