package com.mywebsite.users;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WebsiteUser {

    @NotBlank(message = "User name is not set")
    private String name;

    @NotBlank(message = "User surname is not set")
    private String surname;

    @NotBlank(message = "User password is not set")
    private String password;

    @NotBlank(message = "User email is not set")
    private String email;
}
