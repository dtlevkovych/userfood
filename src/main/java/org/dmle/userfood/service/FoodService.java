package org.dmle.userfood.service;

import org.dmle.userfood.domain.Food;
import org.dmle.userfood.domain.FoodDTO;

import java.util.List;
import java.util.Map;

public interface FoodService {

    List<Food> getFoods(String phrase);
    List<Food> getFoodsPagination(Integer start, Integer limit);
    Food getFoodById(String foodId);
    String addFood(FoodDTO newFood);
    Boolean updateFood(String foodId, FoodDTO updateFood);
    Boolean deleteFood(String foodId);
}
