package org.dmle.userfood.controller;

import org.dmle.userfood.domain.Food;
import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.service.FoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;

public class FoodControllerTest {

    @InjectMocks
    private FoodController controller;

    @Mock
    private FoodService foodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFoods_returnsResponseEntityInstance() {
        String phrase = "";

        ResponseEntity<List<Food>> result = controller.getFoods(phrase);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getFoods_returnsResponseEntityOKStatus() {
        String phrase = "";

        ResponseEntity<List<Food>> result = controller.getFoods(phrase);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getFoods_returnsResponseEntityWithFoods() {
        List<Food> foods = List.of(new Food(), new Food());
        String phrase = "";

        Mockito.when(foodService.getFoods(phrase)).thenReturn(foods);

        ResponseEntity<List<Food>> result = controller.getFoods(phrase);
        Assertions.assertEquals(foods.size(), result.getData().size());
    }

    @Test
    public void getFoodsPagination_returnsResponseEntityInstance() {
        Integer limit = 2;
        Integer page = 1;

        ResponseEntity<List<Food>> result = controller.getFoodsPagination(limit, page);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getFoodsPagination_returnsResponseEntityOKStatus() {
        Integer limit = 2;
        Integer page = 1;

        ResponseEntity<List<Food>> result = controller.getFoodsPagination(limit, page);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getFoodsPagination_returnsResponseEntityWithFoods() {
        List<Food> foods = List.of(new Food(), new Food());
        Integer start = 2;
        Integer limit = 2;
        Integer page = 1;

        Mockito.when(foodService.getFoodsPagination(start, limit)).thenReturn(foods);

        ResponseEntity<List<Food>> result = controller.getFoodsPagination(limit, page);
        Assertions.assertEquals(foods.size(), result.getData().size());
    }

    @Test
    public void getFoodById_returnsResponseEntityInstance() {
        ResponseEntity<Food> result = controller.getFoodById("");
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getFoodById_returnsResponseEntityOKStatus() {
        ResponseEntity<Food> result = controller.getFoodById("");
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getFoodById_returnsResponseEntityWithFoods() {
        Food food = new Food();
        String foodId = "";

        Mockito.when(foodService.getFoodById(foodId)).thenReturn(food);

        ResponseEntity<Food> result = controller.getFoodById(foodId);
        Assertions.assertEquals(food, result.getData());
    }

    @Test
    public void addFood_returnsResponseEntityInstance() {
        ResponseEntity<String> result = controller.addFood(Mockito.anyMap());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void addFood_returnsResponseEntityOKStatus() {
        ResponseEntity<String> result = controller.addFood(Mockito.anyMap());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void addFood_returnsResponseEntityWithId() {
        String foodId = "";

        Mockito.when(foodService.addFood(Mockito.anyMap())).thenReturn(foodId);

        ResponseEntity<String> result = controller.addFood(Mockito.anyMap());
        Assertions.assertEquals(foodId, result.getData());
    }

    @Test
    public void updateFood_returnsResponseEntityInstance() {
        ResponseEntity<Boolean> result = controller.updateFood(Mockito.anyString(), Mockito.anyMap());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void updateFood_returnsResponseEntityOKStatus() {
        ResponseEntity<Boolean> result = controller.updateFood(Mockito.anyString(), Mockito.anyMap());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateFood_returnsResponseEntityWithTrue() {
        Boolean status = true;

        Mockito.when(foodService.updateFood(Mockito.anyString(), Mockito.anyMap())).thenReturn(status);

        ResponseEntity<Boolean> result = controller.updateFood(Mockito.anyString(), Mockito.anyMap());
        Assertions.assertEquals(status, result.getData());
    }

    @Test
    public void deleteFood_returnsResponseEntityInstance() {
        ResponseEntity<Boolean> result = controller.deleteFood(Mockito.anyString());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void deleteFood_returnsResponseEntityOKStatus() {
        ResponseEntity<Boolean> result = controller.deleteFood(Mockito.anyString());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void deleteFood_returnsResponseEntityWithTrue() {
        Boolean status = true;

        Mockito.when(foodService.deleteFood(Mockito.anyString())).thenReturn(status);

        ResponseEntity<Boolean> result = controller.deleteFood(Mockito.anyString());
        Assertions.assertEquals(status, result.getData());
    }
}
