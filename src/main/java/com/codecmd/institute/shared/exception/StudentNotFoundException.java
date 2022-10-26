package com.codecmd.institute.shared.exception;

import static com.codecmd.institute.shared.exception.ErrorConstants.STUDENT;
import static com.codecmd.institute.shared.exception.ErrorConstants.STUDENT_NOT_FOUND;

public class StudentNotFoundException extends NotFoundAlertException {
    public StudentNotFoundException() {
        super("Student Not Found", STUDENT, STUDENT_NOT_FOUND);
    }
}
