package com.codecmd.institute.web.rest;

import com.codecmd.institute.service.CourseService;
import com.codecmd.institute.service.StudentService;
import com.codecmd.institute.service.criteria.CourseCriteria;
import com.codecmd.institute.service.dto.CourseDTO;
import com.codecmd.institute.util.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseResource {

    private final CourseService courseService;

    /**
     * {@code GET  /courses} : get all the courses.
     *
     * @param criteria the filter information.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courses in body.
     */
    @GetMapping("/courses")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<CourseDTO>> getAllCourses(@ParameterObject CourseCriteria criteria,@ParameterObject Pageable pageable) {
        Page<CourseDTO> page = courseService.findAll(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
