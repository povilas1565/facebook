package com.example.facebook.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MessageDTO {
    private Long id;

    private String username;

    @NotEmpty
    private String message;

}

