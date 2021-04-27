package com.sparkers.companymanager.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class WhateverException extends RuntimeException {
    private HttpStatus status;
    public WhateverException() {
        super("... whatever internal error happened ...");
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}