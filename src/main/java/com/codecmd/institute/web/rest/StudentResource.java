package com.codecmd.institute.web.rest;

import com.codecmd.institute.service.StudentService;
import com.codecmd.institute.service.dto.CourseDTO;
import com.codecmd.institute.service.dto.StudentDTO;
import com.codecmd.institute.util.HeaderUtil;
import com.codecmd.institute.web.rest.error.StudentNotFoundException;
import com.codecmd.institute.web.rest.request.StudentRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService studentService;

    private static final String ENTITY_NAME = "student";

    @Value("${spring.application.name}")
    private String applicationName;


    /**
     * {@code POST  /students/register} : register the student.
     *
     * @param request the Student Request data..
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studentDTO,
     */
    @PostMapping("/students/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDTO> register(@Valid @RequestBody StudentRequest request) throws URISyntaxException {
        StudentDTO result = studentService.create(request);
        return ResponseEntity
                .created(new URI("/api/students/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result);
    }


    /**
     * {@code PUT  /students/update} : Updates an existing student.
     *
     * @param request the student to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studentDTO,
     * or with status {@code 400 (Bad Request)} if the studentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the studentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/students/update")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentRequest request) throws URISyntaxException {
        StudentDTO result = studentService.update(request).orElseThrow(StudentNotFoundException::new);
        return ResponseEntity
                .created(new URI("/api/students/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result);
    }

    /**
     * {@code GET  /students/my-courses} : get my courses current student.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the studentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/students/my-courses")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Set<CourseDTO>> myCourses() {
        return ResponseEntity.ok(studentService.myCourses());
    }

    /**
     * {@code POST  /students/enroll-courses} : enroll in courses.
     *
     * @param courses course id list
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studentDTO,
     */
    @PostMapping("/students/enroll-courses")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<StudentDTO> enrollCourses(@Valid @RequestBody List<String> courses) {
        StudentDTO result = studentService.enrollCourses(courses).orElseThrow(StudentNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET  /students/:id} : get the current student.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the studentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/students")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<StudentDTO> getStudent() {
        StudentDTO studentDTO = studentService.findOne().orElseThrow(StudentNotFoundException::new);
        return ResponseEntity.ok(studentDTO);
    }
}
