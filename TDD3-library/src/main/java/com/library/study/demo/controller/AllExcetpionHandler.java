package com.library.study.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class AllExcetpionHandler {

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> noAdmin(){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorMessage.notAdminMessage);
    }
}
