package com.apna_bazaar.Apna_Bazaar.exception;

import com.apna_bazaar.Apna_Bazaar.payload.response.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalException {

    ExceptionResponseDto response = new ExceptionResponseDto();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myArgumentsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(error.getObjectName(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // ----------- CUSTOM EXCEPTIONS -----------
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> myResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
        response.setMessage(ex.getMessage());
        response.setErrorType("ALREADY_EXISTS");
        response.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public ResponseEntity<ExceptionResponseDto> myResourceNotExistException(ResourceNotExistException ex){
        response.setMessage(ex.getMessage());
        response.setErrorType("RESOURCE_NOT_FOUND");
        response.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
