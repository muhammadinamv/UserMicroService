package com.project.usermicroservice.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
