package com.cursomateus.pizzariadankicode.demo.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
