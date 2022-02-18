package com.example.facebook.controllers;

import com.example.facebook.dto.PhoneDTO;
import com.example.facebook.entity.Phone;
import com.example.facebook.facade.PhoneFacade;

import com.example.facebook.service.PhoneService;
import com.example.facebook.validators.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/phone")
@CrossOrigin
public class PhoneController {
        @Autowired
        private PhoneFacade phoneFacade;

        @Autowired
        private PhoneService phoneService;

        @Autowired
        private ResponseErrorValidator responseErrorValidator;


    @PostMapping("/create")
    public ResponseEntity<Object> createPhone(@Valid @RequestBody PhoneDTO phoneDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> listErrors = responseErrorValidator.mappedValidatorService(bindingResult);

        Phone phone = phoneService.createPhone(phoneDTO, principal);
        PhoneDTO phoneCreated = phoneFacade.phoneToPhoneDTO(phone);

        return new ResponseEntity<>(phoneCreated, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhoneDTO>> getAllPhones() {
        List<PhoneDTO> PhoneDTOList= phoneService.getAllPhones()
                .stream()
                .map(phoneFacade::phoneToPhoneDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(PhoneDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/phones")
    public ResponseEntity<List<PhoneDTO>> getAllPhonesForUser(Principal principal) {
        List<PhoneDTO> phoneDTOList = phoneService.getAllPhonesForUser(principal)
                .stream()
                .map(phoneFacade::phoneToPhoneDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(phoneDTOList, HttpStatus.OK);
    }
}





