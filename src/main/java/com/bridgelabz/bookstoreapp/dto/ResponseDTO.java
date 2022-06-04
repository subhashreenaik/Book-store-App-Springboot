package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

@Data
public class ResponseDTO {
	String message;
    Object data;
    public String token;

    public ResponseDTO(String message, Object data,String token) {
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
        
    }
}
