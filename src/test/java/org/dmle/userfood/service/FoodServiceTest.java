package org.dmle.userfood.service;

import org.dmle.userfood.domain.Food;
import org.dmle.userfood.domain.FoodDTO;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserDTO;
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


        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("Some Name");
        foodDTO.setRateId("Some Rate id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        String newFoodId = service.addFood(foodDTO);

        Assertions.assertEquals(foodId, newFoodId);
    }

    @Test
    public void addFood_nonValidName_throwsException() {
        String foodId = "123-235673-123";

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setRateId("Some Rate id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addFood(foodDTO));
    }

    @Test
    public void addFood_nonValidRateId_throwsException() {
        String foodId = "123-235673-123";

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("Some Name");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addFood(foodDTO));
    }

    @Test
    public void addFood_validFoodNotExist_returnsFoodId() {
        String foodId = "123-235673-123";

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("Some Name");
        foodDTO.setRateId("Some Rate id");

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        String newFoodId = service.addFood(foodDTO);

        Assertions.assertEquals(newFoodId, foodId);
    }

    @Test
    public void addFood_nonValidFoodExist_throwsException() {
        String foodId = "123-235673-123";

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("Some Name");
        foodDTO.setRateId("Some Rate id");

        Food food = new Food();
        food.setId("1");
        food.setName(foodDTO.getName());
        food.setRateId(foodDTO.getRateId());

        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(List.of(food));
        Mockito.when(foodRepository.addFood(Mockito.any())).thenReturn(foodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addFood(foodDTO));
    }

    @Test
    public void updateFood_returnsBooleanInstance() {
        String foodId = "123-235673-123";
        Boolean status = true;

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName("Some Name");
        foodDTO.setRateId("Some Rate id");

        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(new Food());
        Mockito.when(foodRepository.getFoodsByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(foodRepository.updateFood(Mockito.anyString(), Mockito.any())).thenReturn(status);

        Boolean result = service.updateFood(foodId, foodDTO);

        Assertions.assertEquals(result, status);
    }

    @Test
    public void deleteFood_returnsBooleanInstance() {
        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(new Food());

        Boolean result = service.deleteFood("6t6wer8d7q");
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void deleteFood_nonExistFood_throwsException() {
        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteFood(Mockito.anyString()));
    }

    @Test
    public void deleteFood_existFood_returnsTrue() {
        Boolean status = true;

        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(new Food());
        Mockito.when(foodRepository.deleteFood(Mockito.anyString())).thenReturn(status);

        Boolean result = service.deleteFood(Mockito.anyString());
        Assertions.assertEquals(result, status);
    }
}
