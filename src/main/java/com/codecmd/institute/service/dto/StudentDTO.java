package com.codecmd.institute.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A student.
 */
@Getter
@Setter
public class StudentDTO implements Serializable {

    private String id;

    private String login;

    private String firstName;

    private String lastName;

    private String email;

    private String imageUrl;
}
