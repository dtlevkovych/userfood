package org.dmle.userfood.service;

import org.dmle.userfood.domain.Food;
import org.dmle.userfood.repo.FoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @InjectMocks
    private FoodServiceImpl service;

    @Mock
    private FoodRepository foodRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFoods_returnsListInstance() {
        String phrase = "";

        List<Food> result = service.getFoods(phrase);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getFoods_returnsListWithFoods() {
        List<Food> foods = List.of(new Food(), new Food());
        String phrase = "";

        Mockito.when(foodRepository.getFoods(phrase)).thenReturn(foods);

        List<Food> result = service.getFoods(phrase);
        Assertions.assertEquals(foods.size(), result.size());
    }

    @Test
    public void getFoodsPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;

        List<Food> result = service.getFoodsPagination(start, limit);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getFoodsPagination_returnsListWithFoods() {
        List<Food> foods = List.of(new Food(), new Food());
        Integer start = 2;
        Integer limit = 2;

        Mockito.when(foodRepository.getFoodsPagination(start, limit)).thenReturn(foods);

        List<Food> result = service.getFoodsPagination(start, limit);
        Assertions.assertEquals(foods.size(), result.size());
    }

    @Test
    public void getFoodById_returnsListInstance() {
        String foodId = "";

        Mockito.when(foodRepository.getFoodById(foodId)).thenReturn(new Food());

        Food result = service.getFoodById(foodId);
        Assertions.assertInstanceOf(Food.class, result);
    }

    @Test
    public void getFoodById_returnsListWithFoods() {
        Food food = new Food();

        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(food);

        Food result = service.getFoodById("");
        Assertions.assertEquals(food, result);
    }

    @Test
    public void addFood_validFood_returnsNewFoodId() {
        String foodId = "123-235673-123";

        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put(FoodServiceImpl.NAME, "Some Name");
        foodMap.put(FoodServiceImpl.RATE_ID, "Some Rate id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        String newFoodId = service.addFood(foodMap);

        Assertions.assertEquals(foodId, newFoodId);
    }

    @Test
    public void addFood_nonValidName_throwsException() {
        String foodId = "123-235673-123";

        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put(FoodServiceImpl.RATE_ID, "Some Rate id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addFood(foodMap));
    }

    @Test
    public void addFood_nonValidRateId_throwsException() {
        String foodId = "123-235673-123";

        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put(FoodServiceImpl.NAME, "Some Name");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addFood(foodMap));
    }

    @Test
    public void addFood_validFoodNotExist_returnsFoodId() {
        String foodId = "123-235673-123";

        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put(FoodServiceImpl.NAME, "Some Name");
        foodMap.put(FoodServiceImpl.RATE_ID, "Some Rate Id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        String newFoodId = service.addFood(foodMap);

        Assertions.assertEquals(newFoodId, foodId);
    }

    @Test
    public void addFood_nonValidFoodExist_throwsException() {
        String foodId = "123-235673-123";

        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put(FoodServiceImpl.NAME, "Some Name");
        foodMap.put(FoodServiceImpl.RATE_ID, "Some Rate Id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(List.of(new Food()));
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addFood(foodMap));
    }

    @Test
    public void updateFood_returnsBooleanInstance() {
        String foodId = "123-235673-123";
        Boolean status = true;

        Map<String, Object> foodMap = new HashMap<>();
        foodMap.put(FoodServiceImpl.NAME, "Some Name");
        foodMap.put(FoodServiceImpl.RATE_ID, "Some Rate Id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.updateFood(Mockito.anyString(), Mockito.any())).thenReturn(status);

        Boolean result = service.updateFood(foodId, foodMap);

        Assertions.assertEquals(result, status);
    }
}
