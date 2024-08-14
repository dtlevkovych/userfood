package org.dmle.userfood.repo;

import org.dmle.userfood.domain.Food;

import java.util.List;

public interface FoodRepository {

    List<Food> getFoods(String phrase);
    List<Food> getFoodsPagination(Integer start, Integer limit);
    Food getFoodById(String foodId);
    List<Food> getFoodsByName(String name);
    String addFood(Food newFood);
    Boolean updateFood(String foodId, Food updateFood);
}
