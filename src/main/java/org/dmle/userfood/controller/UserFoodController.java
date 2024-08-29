package org.dmle.userfood.controller;

import org.dmle.userfood.config.ApiPrefixController;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.service.UserFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiPrefixController
public class UserFoodController {
    @Autowired
    private UserFoodService userFoodService;

    @GetMapping(value = "userfoods")
    public ResponseEntity<List<UserFood>> getUserFoods() {
        return ResponseEntity.successResponse(userFoodService.getUserFoods());
    }

    @GetMapping(value = "userfoods/user/{id}")
    public ResponseEntity<List<UserFood>> getUserFoodsByUserId(@PathVariable("id") String userId) {
        return ResponseEntity.successResponse(userFoodService.getUserFoodsByUserId(userId));
    }

    @GetMapping(value = "userfoods/user/{id}/pagination")
    public ResponseEntity<List<UserFood>> getUserFoodsByUserIdPagination(@PathVariable("id") String userId, @RequestParam int limit, @RequestParam int page) {
        if (limit == 0) {
            limit = 10;
        }

        if (page == 0) {
            page = 0;
        }

        Integer start = page * limit;

        return ResponseEntity.successResponse(userFoodService.getUserFoodsByUserIdPagination(start, limit, userId));
    }
}
