package com.codecmd.institute.web.rest.error;

import static com.codecmd.institute.web.rest.error.ErrorConstants.STUDENT;
import static com.codecmd.institute.web.rest.error.ErrorConstants.STUDENT_NOT_FOUND;

public class StudentNotFoundException extends NotFoundAlertException {
    public StudentNotFoundException() {
        super("Student Not Found", STUDENT, STUDENT_NOT_FOUND);
    }
}
