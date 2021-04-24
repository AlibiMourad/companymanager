package com.sparkers.companymanager.controller;

import com.sparkers.companymanager.dto.PartnerDto;
import com.sparkers.companymanager.services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    // Get All partners : //
    @GetMapping
    public ResponseEntity<List<PartnerDto>> listAllPartners(@RequestParam(defaultValue = "0") int from,
                                                            @RequestParam(defaultValue = "10") int size) {
        List<PartnerDto> partners = partnerService.getAllPartner(from, size);
        if (partners.isEmpty()) {
            return new ResponseEntity<List<PartnerDto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PartnerDto>>(partners, HttpStatus.OK);
    }

    // Get  partners by ID : //
    @GetMapping("{id}")
    public ResponseEntity<PartnerDto> getPartner(@PathVariable(required = true) Long id) {
        Optional<PartnerDto> partner = partnerService.getPartner(id);
        if (!partner.isPresent()) {
            return new ResponseEntity<PartnerDto>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<PartnerDto>(partner.get(), HttpStatus.OK);
    }

    // Add  partners by ID : //
    @PostMapping
    public ResponseEntity<PartnerDto> addPartner(@RequestBody PartnerDto partnerDto) {
        if (partnerDto == null) {
            return new ResponseEntity<PartnerDto>(HttpStatus.NO_CONTENT);
        }
        PartnerDto newPartnerDto = partnerService.createPartner(partnerDto);
        return new ResponseEntity<PartnerDto>(newPartnerDto, HttpStatus.OK);
    }

    // UPDATE partners by ID : //
    @PutMapping("{id}")
    public ResponseEntity<PartnerDto> updatePartner(@PathVariable(required = true) Long id, @RequestBody PartnerDto partnerDto) {
        if (partnerDto == null) {
            return new ResponseEntity<PartnerDto>(HttpStatus.NO_CONTENT);
        }
        PartnerDto updatedPartnerDto = partnerService.updatePartner(id, partnerDto);
        return new ResponseEntity<PartnerDto>(updatedPartnerDto, HttpStatus.OK);
    }

    // DELETE  partners by ID : //
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePartner(@PathVariable(required = true) Long id) {
        partnerService.deletePartner(id);
        return new ResponseEntity<String>("The partner with the given "+ id +" has been deleted", HttpStatus.OK);

    }
    // Get All partners : //
   /* @PostMapping("/partners")
    public ResponseEntity<Partner> istAllPartners( @RequestBody PartnerDto partnerDto) {


         Partner partner = DtoTransformer.transform(partnerDto);
/*
        if (partnerRepository.save(partner) != null) {
            return new ResponseEntity<Partner>(HttpStatus.NO_CONTENT);
        }*/
    //return new ResponseEntity<Partner>(partner, HttpStatus.OK);
    //}*/
}
