package com.jogo.tochat.services;

import com.jogo.tochat.entities.User;
import com.jogo.tochat.exceptions.NotFoundException;
import com.jogo.tochat.repositories.UserRepository;
import com.jogo.tochat.utils.GetUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserById(Integer id) throws NotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found with id: " + id);
        } else {
            return user;
        }
    }

    public Optional<User> getUserByToken() throws NotFoundException {
        GetUserUtil userUtil = new GetUserUtil();
        String userName = userUtil.GetUserName();
        Optional<User> user = this.userRepository.findUserByEmail(userName);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found with id: " + userName);

        }
        return user;
    }
}
