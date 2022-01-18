package com.example.facebook.payload.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTSuccessResponse {
    private boolean success;
    private String token;
}
