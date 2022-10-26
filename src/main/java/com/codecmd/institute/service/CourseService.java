package com.codecmd.institute.service;

import com.codecmd.institute.domain.Course;
import com.codecmd.institute.domain.Course_;
import com.codecmd.institute.repository.CourseRepository;
import com.codecmd.institute.service.criteria.CourseCriteria;
import com.codecmd.institute.service.dto.CourseDTO;
import com.codecmd.institute.service.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.codecmd.institute.shared.Constants.searchFormat;

/**
 * Service Implementation for managing {@link Course}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    /**
     * Get all the courses.
     *
     * @param criteria the filter information.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CourseDTO> findAll(CourseCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Courses");
        return courseRepository.findAll(createSpecification(criteria), pageable).map(courseMapper::toDto);
    }

    private Specification<Course> createSpecification(CourseCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getName() != null) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get(Course_.name)), String.format(searchFormat, criteria.getName())),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Course_.description)), String.format(searchFormat, criteria.getName()))));
            }

            if (criteria.getInstructor() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(Course_.instructor)), String.format(searchFormat, criteria.getInstructor())));
            }

            if (criteria.getDifficultyLevel() != null) {
                predicates.add(criteriaBuilder.equal(root.get(Course_.difficultyLevel), criteria.getDifficultyLevel()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
