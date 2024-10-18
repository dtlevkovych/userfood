package org.dmle.userfood.controller;

import org.dmle.userfood.config.ApiPrefixController;
import org.dmle.userfood.domain.Food;
import org.dmle.userfood.domain.FoodDTO;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ApiPrefixController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping(value = "foods")
    public ResponseEntity<List<Food>> getFoods(@RequestParam String phrase) {
        return ResponseEntity.successResponse(foodService.getFoods(phrase));
    }

    @GetMapping(value = "foods/pagination")
    public ResponseEntity<List<Food>> getFoodsPagination(@RequestParam int limit, @RequestParam int page) {
        if (limit == 0) {
            limit = 10;
        }

        if (page == 0) {
            page = 0;
        }

        Integer start = page * limit;

        return ResponseEntity.successResponse(foodService.getFoodsPagination(start, limit));
    }

    @GetMapping(value = "food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") String foodId) {
        return ResponseEntity.successResponse(foodService.getFoodById(foodId));
    }

    @PostMapping(value = "food")
    public ResponseEntity<String> addFood(@RequestBody FoodDTO newFood) {
        return ResponseEntity.successResponse(foodService.addFood(newFood));
    }

    @PutMapping(value = "food/{id}")
    public ResponseEntity<Boolean> updateFood(@PathVariable("id") String foodId, @RequestBody FoodDTO updateFood) {
        return ResponseEntity.successResponse(foodService.updateFood(foodId, updateFood));
    }

    @DeleteMapping(value = "food/{id}")
    public ResponseEntity<Boolean> deleteFood(@PathVariable("id") String foodId) {
        return ResponseEntity.successResponse(foodService.deleteFood(foodId));
    }
}
