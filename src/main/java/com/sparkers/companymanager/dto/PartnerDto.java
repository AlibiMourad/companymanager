package com.sparkers.companymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String reference;
    @NotBlank
    @Pattern(regexp = "[a-z]{2}_[A-Z]{2}")
    private String locale;
    private Date expirationTime;
}
