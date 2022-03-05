package com.example.facebook.facade;

import com.example.facebook.dto.CommentDTO;
import com.example.facebook.dto.PhoneDTO;

import com.example.facebook.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneFacade {
    public PhoneDTO phoneToPhoneDTO(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(phone.getId());
        phoneDTO.setTitle(phone.getTitle());
        phoneDTO.getLocation(phone.getLocation());
        phoneDTO.setUsername(phone.getUser().getUsername());

        return phoneDTO;
    }
}
