package com.diploma.tablet_manager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {

    @ApiModelProperty(notes = "The user login")
    private String login;

    @ApiModelProperty(notes = "The user password")
    private String password;

    @ApiModelProperty(notes = "The user email")
    private String email;
}
