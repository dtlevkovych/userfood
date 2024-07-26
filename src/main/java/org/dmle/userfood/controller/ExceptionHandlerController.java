package org.dmle.userfood.controller;

import org.dmle.userfood.domain.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionHandlerController {

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> exceptionHandler(String error) {
        return ResponseEntity.errorResponse(error, HttpStatus.BAD_REQUEST);
    }
}
