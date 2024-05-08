package com.project.usermicroservice.dtos;

import com.project.usermicroservice.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;
}
