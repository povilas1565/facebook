package com.example.facebook.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "paolo";
        this.password = "paul9955";
    }
}
