package org.dmle.userfood.service;

import org.dmle.userfood.domain.Food;

import java.util.List;
import java.util.Map;

public interface FoodService {

    List<Food> getFoods(String phrase);
    List<Food> getFoodsPagination(Integer start, Integer limit);
    Food getFoodById(String foodId);
    String addFood(Map<String, Object> newFood);
    Boolean updateFood(String foodId, Map<String, Object> updateFood);
}
