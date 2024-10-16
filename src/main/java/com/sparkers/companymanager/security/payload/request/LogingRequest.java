package com.sparkers.companymanager.security.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LogingRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
