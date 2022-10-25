package com.codecmd.institute.service.criteria;

import com.codecmd.institute.domain.enumeration.DifficultyLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A course.
 */
@Getter
@Setter
public class CourseCriteria implements Serializable {

    private String name;

    private DifficultyLevel difficultyLevel;

    private String instructor;
}
