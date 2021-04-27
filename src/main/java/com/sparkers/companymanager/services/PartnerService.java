package com.sparkers.companymanager.services;

import com.sparkers.companymanager.converter.PartnerConverter;
import com.sparkers.companymanager.dto.PartnerDto;
import com.sparkers.companymanager.exception.PartnerNotFoundException;
import com.sparkers.companymanager.exception.ValueValidateException;
import com.sparkers.companymanager.exception.WhateverException;
import com.sparkers.companymanager.model.Partner;
import com.sparkers.companymanager.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {
    @Autowired
    PartnerRepository partnerRepository;
    @Autowired
    PartnerConverter partnerConverter;

    public List<PartnerDto> getAllPartner(int from, int size) throws WhateverException {
        try {
            return partnerConverter.entityListToDtoList(partnerRepository.findAll(PageRequest.of(from, size)).getContent());
        } catch (Exception e) {
            throw new WhateverException();
        }
    }

    public PartnerDto getPartner(Long id) {
        Optional<Partner> partner;
        try {
            partner = partnerRepository.findById(id);
        } catch (Exception e) {
            throw new WhateverException();
        }
        if (partner.isPresent()) {
            try {
                return partnerConverter.entityToDto(partner.get());
            } catch (Exception e) {
                throw new WhateverException();
            }
        } else {
            throw new PartnerNotFoundException(id);
        }
    }

    public PartnerDto createPartner(PartnerDto partnerDto) {
        try {
            return partnerConverter.entityToDto(partnerRepository.save(partnerConverter.dtoToEntity(partnerDto)));
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            throw new ValueValidateException();
        } catch (Exception e) {
            throw new WhateverException();
        }
    }

    public PartnerDto updatePartner(Long id, PartnerDto partnerDto) {
        PartnerDto partnerUpdated = getPartner(id);
        try {
            partnerUpdated.setName(partnerDto.getName());
            partnerUpdated.setReference(partnerDto.getReference());
            partnerUpdated.setLocale(partnerDto.getLocale());
            partnerUpdated.setExpirationTime(partnerDto.getExpirationTime());
            return partnerConverter.entityToDto(partnerRepository.saveAndFlush(partnerConverter.dtoToEntity(partnerUpdated)));
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            throw new ValueValidateException();
        } catch (Exception e) {
            throw new WhateverException();
        }
    }

    public void deletePartner(Long id) {
        Optional<Partner> partner;
        try {
            partner = partnerRepository.findById(id);
        } catch (Exception e) {
            throw new WhateverException();
        }
        if (partner.isPresent()) {
            try {
                partnerRepository.deleteById(id);
            } catch (Exception e) {
                throw new WhateverException();
            }
        } else {
            throw new PartnerNotFoundException(id);
        }

    }
}
