package com.rest.api.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal error ...")
public class InternalServerErrorExeption extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 6202247032118114570L;

    public InternalServerErrorExeption(String message) {
        super(message);
    }

}