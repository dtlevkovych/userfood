package org.dmle.userfood.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.dmle.userfood.domain.Food;
import org.dmle.userfood.domain.FoodDTO;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserDTO;
import org.dmle.userfood.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MapperService mapperService;

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
    public String addFood(FoodDTO newFood) {
        return foodRepository.addFood(validateFood(newFood));
    }

    @Override
    public Boolean updateFood(String foodId, FoodDTO updateFood) {
        return foodRepository.updateFood(foodId, validateFood(foodId, updateFood));
    }

    private Food validateFood(FoodDTO foodDTO) {
        return validateFood(null, foodDTO);
    }

    private Food validateFood(String foodId, FoodDTO foodDTO) {

        Food food = Objects.nonNull(foodId)
                ? Optional.of(foodRepository.getFoodById(foodId)).orElseThrow(() -> new IllegalArgumentException("Food not found"))
                : new Food();

        mapperService.updateValues(food, foodDTO);

        if (StringUtils.isBlank(food.getName())) {
            throw new IllegalArgumentException("Name");
        }

        if (StringUtils.isBlank(food.getRateId())) {
            throw new IllegalArgumentException("Rate id");
        }

        List<Food> existingFoods = foodRepository.getFoodsByName(food.getName());

        if (!existingFoods.isEmpty()) {
            for (Food existingFood : existingFoods) {
                if (!Objects.equals(existingFood.getId(), food.getId())) {
                    throw new IllegalArgumentException("Food with such name allready exist");
                }
            }
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
