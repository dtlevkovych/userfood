package org.dmle.userfood.controller;

import org.dmle.userfood.config.ApiPrefixController;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ApiPrefixController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.successResponse(userService.getUsers());
    }

    @GetMapping(value = "users/pagination")
    public ResponseEntity<List<User>> getUsersPagination(@RequestParam int limit, @RequestParam int page) {
        if (limit == 0) {
            limit = 10;
        }

        if (page == 0) {
            page = 0;
        }

        Integer start = page * limit;

        return ResponseEntity.successResponse(userService.getUsersPagination(start, limit));
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<List<User>> getUser(@PathVariable("id") String userId) {
        return ResponseEntity.successResponse(userService.getUser(userId));
    }

    @PostMapping(value = "user")
    public ResponseEntity<String> addUser(@RequestBody Map<String, Object> newUser) {
        return ResponseEntity.successResponse(userService.addUser(newUser));
    }
}
