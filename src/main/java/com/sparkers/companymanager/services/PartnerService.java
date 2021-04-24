package com.sparkers.companymanager.services;


import com.sparkers.companymanager.converter.PartnerConverter;
import com.sparkers.companymanager.dto.PartnerDto;
import com.sparkers.companymanager.model.Partner;
import com.sparkers.companymanager.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    PartnerConverter partnerConverter;

    public List<PartnerDto> getAllPartner(int from, int size) {
        return partnerConverter.entityListToDtoList(partnerRepository.findAll(PageRequest.of(from, size)).getContent());
    }

    public Optional<PartnerDto> getPartner(Long id) {
        return Optional.ofNullable(partnerConverter.entityToDto(partnerRepository.findById(id).get()));
    }

    public PartnerDto createPartner(PartnerDto partnerDto) {
        return partnerConverter.entityToDto(partnerRepository.save(partnerConverter.dtoToEntity(partnerDto)));
    }

    public PartnerDto updatePartner(Long id, PartnerDto partnerDto) {
        PartnerDto partnerUpdated = getPartner(id).get();

        partnerUpdated.setName(partnerDto.getName());
        partnerUpdated.setReference(partnerDto.getReference());
        partnerUpdated.setLocale(partnerDto.getLocale());
        partnerUpdated.setExpirationTime(partnerUpdated.getExpirationTime());

        return partnerConverter.entityToDto(partnerRepository.saveAndFlush(partnerConverter.dtoToEntity(partnerUpdated)));
    }

    public void deletePartner(Long id) {
            partnerRepository.deleteById(id);
    }
}
