package com.sparkers.companymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDto  {
    private Long id;
    private String name;
    private String reference;
    private Locale locale;
    private Date expirationTime;
}
