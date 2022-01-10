package com.example.facebook.exceptions;
import com.example.facebook.entity.Image;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
