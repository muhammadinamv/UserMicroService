package com.project.usermicroservice.dtos;

import lombok.Data;

@Data
public class SignUpDto {
    String name;
    String email;
    String password;
}
