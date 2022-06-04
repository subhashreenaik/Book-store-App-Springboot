package com.bridgelabz.bookstoreapp.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;

@ControllerAdvice
public class BookStoreExceptionHandler {
	private static final String message = "Exception while processing REST request";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream()
                                             .map(objectError -> objectError.getDefaultMessage())
                                             .collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO(message, errorMessage, "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ResponseDTO> handleBookStoreCustomException(BookStoreException exception) {
        ResponseDTO responseDTO = new ResponseDTO(message, exception.getMessage(), "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
