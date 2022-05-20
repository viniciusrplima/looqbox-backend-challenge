package com.looqbox.vinicius.pokeapi.dto.response;

import org.springframework.http.HttpStatus;

public class ErrorResponseDTO {

    public int statusCode;
    public String message;

    public ErrorResponseDTO(String message, int statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
