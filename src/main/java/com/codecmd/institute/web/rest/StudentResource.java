package com.codecmd.institute.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentResource {

    @GetMapping("/students")
    public ResponseEntity<String> createUser() {
        return ResponseEntity.ok("Hola");
    }
}
