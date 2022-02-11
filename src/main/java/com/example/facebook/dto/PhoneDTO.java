package com.example.facebook.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PhoneDTO {
    private Long id;

    private String title;

    private String location;

    private String username;

    @NotEmpty
    private String message;


    public void getLocation(String location){

    }
}
