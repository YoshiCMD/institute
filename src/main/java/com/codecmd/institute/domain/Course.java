package com.codecmd.institute.domain;

import com.codecmd.institute.domain.enumeration.DifficultyLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A course.
 */
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 8, max = 512)
    @Column(name = "description", length = 512, nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level", nullable = false)
    private DifficultyLevel difficultyLevel;

    @Size(max = 256)
    @Column(name = "instructor", length = 256, nullable = false)
    private String instructor;
}
