package com.codecmd.institute.rest.controller;

import com.codecmd.institute.service.StudentService;
import com.codecmd.institute.service.dto.CourseDTO;
import com.codecmd.institute.service.dto.StudentDTO;
import com.codecmd.institute.shared.util.HeaderUtil;
import com.codecmd.institute.shared.exception.StudentNotFoundException;
import com.codecmd.institute.rest.controller.request.StudentRequest;
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
     * {@code GET  /students/my-courses} : get all the courses the student is enrolled in.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the studentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/students/my-courses")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<CourseDTO>> myCourses() {
        return ResponseEntity.ok(studentService.myCourses());
    }

    /**
     * {@code GET /students/my-course} : get the students enrolled in the course
     *
     * @param courseId course id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the body studentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/students/by-course/{courseId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<StudentDTO>> studentsByCourse(@PathVariable String courseId) {
        return ResponseEntity.ok(studentService.studentsByCourse(courseId));
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
    public ResponseEntity<List<CourseDTO>> enrollCourses(@Valid @RequestBody List<String> courses) {
        return ResponseEntity.ok(studentService.enrollCourses(courses));
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
