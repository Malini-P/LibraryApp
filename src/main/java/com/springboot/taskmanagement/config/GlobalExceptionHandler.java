package com.springboot.taskmanagement.config;

import com.springboot.taskmanagement.dto.response.ErrorMessageDto;
import com.springboot.taskmanagement.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> list = result.getFieldErrors();
        Map<String, String> map = new HashMap<>();
        list.forEach(err -> map.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.badRequest().body(new ErrorMessageDto(e.getMessage()));
    }
}