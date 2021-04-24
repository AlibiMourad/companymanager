package com.sparkers.companymanager.converter;


import com.sparkers.companymanager.dto.PartnerDto;
import com.sparkers.companymanager.model.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerConverter implements GenericsConverter<Partner, PartnerDto> {
    @Override
    public PartnerDto entityToDto(Partner partner) {
        return PartnerDto.builder()
                .id(partner.getId())
                .name(partner.getCompanyName())
                .reference(partner.getRef())
                .locale(partner.getLocale())
                .expirationTime(partner.getExpires())
                .build();
    }

    @Override
    public Partner dtoToEntity(PartnerDto partner) {
        return Partner.builder()
                .id(partner.getId())
                .companyName(partner.getName())
                .ref(partner.getReference())
                .locale(partner.getLocale())
                .expires(partner.getExpirationTime())
                .build();
    }
}
