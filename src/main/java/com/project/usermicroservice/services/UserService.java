package com.project.usermicroservice.services;

import com.project.usermicroservice.exceptions.SignUpException;
import com.project.usermicroservice.models.Token;
import com.project.usermicroservice.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User signUp(String name, String email, String password) throws SignUpException;
    Token login(String email, String password) throws Exception;

    User validateToken(String token) throws Exception;
}
