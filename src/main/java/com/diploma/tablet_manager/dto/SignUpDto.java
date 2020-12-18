package com.diploma.tablet_manager.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class SignUpDto {
    private String login;
    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
