package org.dmle.userfood.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PingController {

    @GetMapping(value = "/ping")
    public ResponseEntity<Map<String, String>> ping() {
        Map<String, String> result = new HashMap<>();
        result.put("message", "pong");
        ResponseEntity<Map<String, String>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }
}
