package com.codecmd.institute.web.rest;

import com.codecmd.institute.service.StudentService;
import com.codecmd.institute.service.dto.StudentDTO;
import com.codecmd.institute.util.HeaderUtil;
import com.codecmd.institute.web.rest.request.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService studentService;

    private static final String ENTITY_NAME = "student";

    @Value("${spring.application.name}")
    private String applicationName;


    /**
     * {@code POST  /register} : register the student.
     *
     * @param request the managed user View Model.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDTO> registerAccount(@Valid @RequestBody StudentRequest request) throws URISyntaxException {
        StudentDTO result = studentService.create(request);
        return ResponseEntity
                .created(new URI("/api/students/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}
