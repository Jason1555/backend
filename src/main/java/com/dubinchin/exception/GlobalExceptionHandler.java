package com.dubinchin.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponse.builder()
                    .error(e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build()
            );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException e) {
        return ResponseEntity.badRequest()
            .body(
                ErrorResponse.builder()
                    .error(e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build()
            );
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedAccessException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(
                ErrorResponse.builder()
                    .error(e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build()
            );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse.builder()
                    .error("Internal server error")
                    .timestamp(LocalDateTime.now())
                    .build()
            );
    }
}
