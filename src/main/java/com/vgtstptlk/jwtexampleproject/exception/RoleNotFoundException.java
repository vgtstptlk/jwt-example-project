package com.vgtstptlk.jwtexampleproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String message) {
        super(message + " Not Found");
    }
}
