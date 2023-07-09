package com.bdg.pc_build.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<APIError> genericException(
            ResponseStatusException ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                APIError.builder()
                        .errorMessage(ex.getReason())
                        .errorCode(String.valueOf(ex.getStatusCode()))
                        .request(request.getRequestURI())
                        .requestType(request.getMethod())
                        .dateAndTime(String.valueOf(LocalDateTime.now()))
                        .build(), ex.getStatusCode()
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<APIError> genericException(
            Exception ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                APIError.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .request(request.getRequestURI())
                        .requestType(request.getMethod())
                        .dateAndTime(String.valueOf(LocalDateTime.now()))
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<APIError> genericException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                APIError.builder()
                        .errorMessage(ex.getFieldError().getDefaultMessage())
                        .errorCode(ex.getStatusCode().toString())
                        .request(request.getRequestURI())
                        .requestType(request.getMethod())
                        .dateAndTime(String.valueOf(LocalDateTime.now()))
                        .build(), ex.getStatusCode()
        );
    }
}