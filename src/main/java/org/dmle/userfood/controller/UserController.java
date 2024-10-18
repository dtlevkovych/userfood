package org.dmle.userfood.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.dmle.userfood.config.ApiPrefixController;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserDTO;
import org.dmle.userfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        return ResponseEntity.successResponse(userService.getUserById(userId));
    }

    @PostMapping(value = "user")
    public ResponseEntity<String> addUser(@RequestBody UserDTO newUser) {
        return ResponseEntity.successResponse(userService.addUser(newUser));
    }

    @PutMapping(value = "user/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") String userId, @RequestBody UserDTO updateUser) {
        return ResponseEntity.successResponse(userService.updateUser(userId, updateUser));
    }
    
    @DeleteMapping(value = "user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String userId) {
        return ResponseEntity.successResponse(userService.deleteUser(userId));
    }
}
