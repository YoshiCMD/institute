package com.codecmd.institute.shared.exception;

public class CourseNotFoundException extends NotFoundAlertException {
    public CourseNotFoundException() {
        super("Course Not Found", ErrorConstants.COURSE, ErrorConstants.COURSE_NOT_FOUND);
    }
}
