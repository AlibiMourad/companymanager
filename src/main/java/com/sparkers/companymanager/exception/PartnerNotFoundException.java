package com.sparkers.companymanager.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class PartnerNotFoundException extends RuntimeException {
    private HttpStatus status;
    public PartnerNotFoundException(Long id) {
        super(String.format("Partner with id %d not found!", id));
        this.status = HttpStatus.NOT_FOUND;
    }
}
