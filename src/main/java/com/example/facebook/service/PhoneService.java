package com.example.facebook.service;

import com.example.facebook.dto.PhoneDTO;
import com.example.facebook.entity.Phone;
import com.example.facebook.entity.User;
import com.example.facebook.exceptions.PhoneNotFoundException;
import com.example.facebook.repository.PhoneRepository;
import com.example.facebook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PhoneService {
    public static final Logger LOG = LoggerFactory.getLogger(PhoneService.class);

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository, UserRepository userRepository) {
        this.phoneRepository = phoneRepository;
        this.userRepository = userRepository;
    }

    public List<Phone> getAllPhones() {
        return phoneRepository.findAllByOrderByCreateDate();
    }

    public List<Phone> getAllPhonesForUser(Principal principal) {
        User user = getUserByPrincipal(principal);
        return phoneRepository.findAllByUserOrderByCreateDateDesc(user);
    }

    public Phone getPhoneById(Long phoneId, Principal principal) {
        User user = getUserByPrincipal(principal);
        return phoneRepository.findPhoneByIdAndUser(phoneId, user)
                .orElseThrow(() -> new PhoneNotFoundException("Phone not found for username:" + user.getEmail()));
    }

    public Phone createPhone(PhoneDTO phoneDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        Phone phone = new Phone();
        phone.setUser(user);
        phone.setTitle(phoneDTO.getTitle());
        phone.setLocation(phoneDTO.getLocation());

        LOG.info("Create new phone for user: {}", user.getEmail());
        return phoneRepository.save(phone);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}




