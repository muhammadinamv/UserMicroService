package com.project.usermicroservice.services;

import com.project.usermicroservice.dtos.UserDto;
import com.project.usermicroservice.exceptions.SignUpException;
import com.project.usermicroservice.models.User;
import com.project.usermicroservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }
    public User signUp(String name, String email, String password) throws SignUpException{
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) throw new SignUpException("Email ID: "+email+" already exists");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(hashedPassword);
        user.setEmailVerified(true);
        user.setRoles(null);
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
