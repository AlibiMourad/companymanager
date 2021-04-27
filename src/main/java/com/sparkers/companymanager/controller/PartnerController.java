package com.sparkers.companymanager.controller;

import com.sparkers.companymanager.dto.PartnerDto;
import com.sparkers.companymanager.exception.ValueValidateException;
import com.sparkers.companymanager.services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    // Get All partners : //
    @GetMapping
    public ResponseEntity<List<PartnerDto>> listAllPartners(@RequestParam(defaultValue = "0") int from,
                                                            @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<List<PartnerDto>>(partnerService.getAllPartner(from, size), HttpStatus.OK);
    }

    // Get  partners by ID : //
    @GetMapping("{id}")
    public ResponseEntity<PartnerDto> getPartner(@PathVariable(required = true) Long id) {
        return new ResponseEntity<PartnerDto>(partnerService.getPartner(id), HttpStatus.OK);
    }

    // Add  New partners: //
    @PostMapping
    public ResponseEntity<PartnerDto> addPartner(@Valid @RequestBody PartnerDto partnerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValueValidateException();
        }
        return new ResponseEntity<PartnerDto>(partnerService.createPartner(partnerDto), HttpStatus.CREATED);
    }


    // UPDATE partners by ID : //
    @PutMapping("{id}")
    public ResponseEntity<PartnerDto> updatePartner(
            @PathVariable(required = true) Long id,
            @Valid @RequestBody PartnerDto partnerDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValueValidateException();
        }
        return new ResponseEntity<PartnerDto>(partnerService.updatePartner(id, partnerDto), HttpStatus.OK);
    }


    // DELETE  partners by ID : //
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePartner(@PathVariable(required = true) Long id) {

        partnerService.deletePartner(id);
        return new ResponseEntity<>(null, HttpStatus.OK);


    }
}
