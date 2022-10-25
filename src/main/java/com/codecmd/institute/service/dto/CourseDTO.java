package com.codecmd.institute.service.dto;

import com.codecmd.institute.domain.enumeration.DifficultyLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A course.
 */
@Getter
@Setter
public class CourseDTO implements Serializable {

    private String id;

    private String name;

    private String description;

    private DifficultyLevel difficultyLevel;

    private String instructor;
}
