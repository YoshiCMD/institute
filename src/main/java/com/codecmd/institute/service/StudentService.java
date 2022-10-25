package com.codecmd.institute.service;

import com.codecmd.institute.domain.Student;
import com.codecmd.institute.repository.CourseRepository;
import com.codecmd.institute.repository.StudentRepository;
import com.codecmd.institute.security.SecurityUtils;
import com.codecmd.institute.service.dto.CourseDTO;
import com.codecmd.institute.service.dto.StudentDTO;
import com.codecmd.institute.service.mapper.CourseMapper;
import com.codecmd.institute.service.mapper.StudentMapper;
import com.codecmd.institute.web.rest.error.StudentNotFoundException;
import com.codecmd.institute.web.rest.request.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing {@link Student}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    private final PasswordEncoder passwordEncoder;

    /**
     * Create a student.
     *
     * @param request the entity to save.
     * @return the persisted entity.
     */
    public StudentDTO create(StudentRequest request) {
        Student student = new Student();
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        student.setLogin(request.getLogin().toLowerCase());
        student.setPassword(encryptedPassword);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        if (request.getEmail() != null) {
            student.setEmail(request.getEmail().toLowerCase());
        }
        student.setImageUrl(request.getImageUrl());
        student = studentRepository.save(student);
        log.debug("Created Information for student: {}", student);
        return studentMapper.toDto(student);
    }

    /**
     * Update a student.
     *
     * @param request the entity to update.
     * @return the persisted entity.
     */
    public Optional<StudentDTO> update(StudentRequest request) {
        return SecurityUtils
                .getCurrentUserLogin()
                .map(studentRepository::findOneByLoginIgnoreCase)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(student -> {
                    student.setLogin(request.getLogin().toLowerCase());
                    student.setFirstName(request.getFirstName());
                    student.setLastName(request.getLastName());
                    if (request.getEmail() != null) {
                        student.setEmail(request.getEmail().toLowerCase());
                    }
                    student.setImageUrl(request.getImageUrl());
                    log.debug("Changed Information for User: {}", student);
                    return studentRepository.save(student);
                })
                .map(studentMapper::toDto);
    }

    /**
     * register.
     *
     * @param courses list courses.
     * @return the persisted entity.
     */
    public Set<CourseDTO> enrollCourses(List<String> courses) {
        return SecurityUtils
                .getCurrentUserLogin()
                .map(studentRepository::findOneByLoginIgnoreCase)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(student -> {
                    student.setCourses(new HashSet<>(courseRepository.findAllById(courses)));
                    return studentRepository.save(student);
                })
                .map(Student::getCourses)
                .map(courseMapper::toDto)
                .orElseThrow(StudentNotFoundException::new);
    }

    /**
     * Get current student.
     *
     * @return the StudentDTO entity.
     */
    @Transactional(readOnly = true)
    public Optional<StudentDTO> findOne() {
        return SecurityUtils
                .getCurrentUserLogin()
                .flatMap(studentRepository::findOneByLoginIgnoreCase)
                .map(studentMapper::toDto);
    }

    /**
     * Get my courses current student.
     *
     * @return the CourseDTO entity.
     */
    @Transactional(readOnly = true)
    public Set<CourseDTO> myCourses() {
        return SecurityUtils
                .getCurrentUserLogin()
                .flatMap(studentRepository::findOneByLoginIgnoreCase)
                .map(Student::getCourses)
                .map(courseMapper::toDto)
                .orElseThrow(StudentNotFoundException::new);
    }
}
