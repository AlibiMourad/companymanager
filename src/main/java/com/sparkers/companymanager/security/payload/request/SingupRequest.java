package com.sparkers.companymanager.security.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
public class SingupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 20)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(max = 120)
    private String password;
}
