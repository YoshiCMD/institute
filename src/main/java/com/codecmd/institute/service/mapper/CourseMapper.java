package com.codecmd.institute.service.mapper;

import com.codecmd.institute.domain.Course;
import com.codecmd.institute.service.dto.CourseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Course} and its DTO {@link CourseDTO}.
 */
@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {}
