package com.diploma.tablet_manager.dto;

import com.diploma.tablet_manager.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AuthorizeJwtDto {

    private String token;
    private Integer id;
    private String email;
    private Set<Role> roles;
}
