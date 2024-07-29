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
        this.userService=userService;
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
    public TokenDto login(@RequestBody LoginDto loginDto) throws Exception{
        Token token = userService.login(loginDto.getEmail(),
                                        loginDto.getPassword());
        TokenDto tokenDto = new TokenDto();
        tokenDto.setValue(token.getValue());
        return tokenDto;
    }
    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token) throws Exception{
        User user = userService.validateToken(token);
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto){
        return null;
    }
}
