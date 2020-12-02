package com.diploma.tablet_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class SignInDto {
    @NotBlank(message = "Login should be valid")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
