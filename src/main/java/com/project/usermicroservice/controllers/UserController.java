package com.project.usermicroservice.controllers;

import com.project.usermicroservice.dtos.*;
import com.project.usermicroservice.models.Token;
import com.project.usermicroservice.models.User;
import com.project.usermicroservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/signUp")
    public UserDto signUp(@RequestBody SignUpDto signUpDto) throws Exception{
        User user = userService.signUp(signUpDto.getName(),
                                signUpDto.getEmail(),
                                signUpDto.getPassword());
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
    @PostMapping("/login")
    public Token login(@RequestBody LoginDto loginDto){
        return null;
    }
    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token){
        return null;
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto){
        return null;
    }
}
