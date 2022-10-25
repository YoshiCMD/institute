package com.codecmd.institute.service.mapper;

import com.codecmd.institute.domain.Student;
import com.codecmd.institute.service.dto.StudentDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {


}
