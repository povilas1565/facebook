package com.example.facebook.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PhoneDTO {
    private Long id;

    private String title;

    @NotEmpty
    private String message;

    @NotEmpty
    private String username;

    public void getLocation(String location){

    }
}
