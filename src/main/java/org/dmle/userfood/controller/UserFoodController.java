package org.dmle.userfood.controller;

import org.dmle.userfood.config.ApiPrefixController;
import org.dmle.userfood.domain.RateReport;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.service.UserFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "userfoods/user/{userId}/food/{foodId}")
    public ResponseEntity<UserFood> getUserFoodById(@PathVariable("userId") String userId, @PathVariable("foodId") String foodId) {
        return ResponseEntity.successResponse(userFoodService.getUserFoodById(userId, foodId));
    }

    @GetMapping(value = "userfoods/eatinghealth/user/{id}")
    public ResponseEntity<List<RateReport>> getEatingHealthReport(@PathVariable("id") String userId) {
        return ResponseEntity.successResponse(userFoodService.getEatingHealthReport(userId));
    }

    @PostMapping(value = "userfoods")
    public ResponseEntity<String> addUserFood(@RequestBody Map<String, Object> newUserFood) {
        return ResponseEntity.successResponse(userFoodService.addUserFood(newUserFood));
    }
}
