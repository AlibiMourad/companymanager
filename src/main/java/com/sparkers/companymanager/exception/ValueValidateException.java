package com.sparkers.companymanager.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ValueValidateException extends RuntimeException {
    private HttpStatus status;
    public ValueValidateException() {
        super(String.format("... A string representing the validation error that occurred ..."));
        this.status = HttpStatus.BAD_REQUEST;
    }
}
