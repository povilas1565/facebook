package com.example.facebook.repository;


import com.example.facebook.entity.Phone;
import com.example.facebook.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findAllByOrderByCreateDate();

    List<Phone> findAllByUserOrderByCreateDateDesc(User user);

    Optional<Phone> findAllPhoneById(Long phoneId);

    Optional<Phone> findAllPhoneByUsername(String username);

    Optional<Phone> findPhoneByIdAndUser(Long id, User user);
}



