package com.example.salonSearcher.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.salonSearcher.service.ConflictException;
import com.example.salonSearcher.service.NotFoundException;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
   @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException exception) {
        return error(HttpStatus.NOT_FOUND, exception.getMessage());
    }

   @ExceptionHandler({ConflictException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, String>> handleConflict(RuntimeException exception) {
        String message = exception instanceof ConflictException
                ? exception.getMessage()
                : "The request conflicts with an existing record.";
        return error(HttpStatus.CONFLICT, message);
    }

     private ResponseEntity<Map<String, String>> error(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
}
