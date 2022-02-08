package com.example.facebook.dto;

import lombok.Data;

@Data
public class VideoDTO {
    private Long id;

    private String title;

    private String location;

    private String username;

    public void getLocation(String location) {
    }
}

