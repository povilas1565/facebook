package com.example.facebook.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MessageDTO {
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String message;

}

