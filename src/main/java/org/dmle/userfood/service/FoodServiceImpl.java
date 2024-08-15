package org.dmle.userfood.service;

import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.dmle.userfood.domain.Food;
import org.dmle.userfood.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService{

    public static final String NAME = "name";
    public static final String RATE_ID = "rateId";

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getFoods(String phrase) {
        return foodRepository.getFoods(phrase);
    }

    @Override
    public List<Food> getFoodsPagination(Integer start, Integer limit) {
        return foodRepository.getFoodsPagination(start, limit);
    }

    @Override
    public Food getFoodById(String foodId) {
        return foodRepository.getFoodById(foodId);
    }

    @Override
    public String addFood(Map<String, Object> newFood) {
        return foodRepository.addFood(validateFood(newFood));
    }

    @Override
    public Boolean updateFood(String foodId, Map<String, Object> updateFood) {
        return foodRepository.updateFood(foodId, validateFood(updateFood));
    }

    private Food validateFood(Map<String, Object> foodMap) {
        Food food = new Food();

        food.setName(MapUtils.getString(foodMap, NAME));
        if (StringUtils.isBlank(food.getName())) {
            throw new IllegalArgumentException("Name");
        }

        food.setRateId(MapUtils.getString(foodMap, RATE_ID));
        if (StringUtils.isBlank(food.getRateId())) {
            throw new IllegalArgumentException("Rate id");
        }

        List<Food> existingFoods = foodRepository.getFoodsByName(food.getName());

        if (!existingFoods.isEmpty()) {
            throw new IllegalArgumentException("Food with such name already exist");
        }

        return food;
    }

    @Override
    public Boolean deleteFood(String foodId) {
        if (foodRepository.getFoodById(foodId) == null) {
            throw new IllegalArgumentException("Not Found");
        }

        return foodRepository.deleteFood(foodId);
    }
}
