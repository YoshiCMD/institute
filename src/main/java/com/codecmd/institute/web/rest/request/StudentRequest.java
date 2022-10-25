package com.codecmd.institute.web.rest.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class StudentRequest implements Serializable {

    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = 8, max = 60)
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    @Override
    public String toString() {
        return "{"
                + "\"login\":\"" + login + "\""
                + ", \"password\":\"" + password + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"imageUrl\":\"" + imageUrl + "\""
                + "}";
    }
}
