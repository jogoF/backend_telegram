package com.jogo.tochat.controllers;

import com.jogo.tochat.entities.User;
import com.jogo.tochat.exceptions.NotFoundException;
import com.jogo.tochat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public Optional<User> getProfile() throws NotFoundException {
        return userService.getUserByToken();
    }
}
