package com.codecmd.institute.web.rest.error;

import org.zalando.problem.Status;

public class NotFoundAlertException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public NotFoundAlertException(String title, String entityName, String detail, String errorKey) {
        super(title, entityName, detail, errorKey, Status.NOT_FOUND);
    }

    public NotFoundAlertException(String title, String entityName, String errorKey) {
        super(title, entityName, errorKey, Status.NOT_FOUND);
    }
}
