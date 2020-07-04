package com.mywebsite.users;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WebsiteUser {

    @NotNull(message = "User name is not set")
    private String name;

    @NotNull(message = "User surname is not set")
    private String surname;

    @NotNull(message = "User password is not set")
    private String password;

    @NotNull(message = "User email is not set")
    private String email;
}
