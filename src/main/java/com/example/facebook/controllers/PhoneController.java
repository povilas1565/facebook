package com.example.facebook.controllers;


import com.example.facebook.dto.PhoneDTO;
import com.example.facebook.dto.UserDTO;
import com.example.facebook.entity.Phone;
import com.example.facebook.entity.User;
import com.example.facebook.facade.PhoneFacade;
import com.example.facebook.service.PhoneService;
import com.example.facebook.validators.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class PhoneController {

    @Autowired
    private PhoneFacade phoneFacade;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @GetMapping("/{userId}")
    public ResponseEntity<PhoneDTO> getUserProfile(@PathVariable("userId") String userId) {
        Phone phone = phoneService.getUserById(Long.parseLong(userId));
        PhoneDTO phoneDTO = phoneFacade.phoneToPhoneDTO(phone);

        return new ResponseEntity<>(phoneDTO, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updatePhone(@Valid @RequestBody PhoneDTO phoneDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> listErrors = responseErrorValidator.mappedValidatorService(bindingResult);
        if (!ObjectUtils.isEmpty(listErrors)) return listErrors;

        Phone phone = phoneService.updatePhone(phoneDTO, principal);
        PhoneDTO phoneUpdated = phoneFacade.phoneToPhoneDTO(phone);

        return new ResponseEntity<>(phoneupdated, HttpStatus.OK);
    }
}


