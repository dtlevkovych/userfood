package org.dmle.userfood.repo;

import org.dmle.userfood.domain.Food;
import org.dmle.userfood.domain.FoodRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FoodRepositoryTest {

    @InjectMocks
    private FoodRepositoryImpl repository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFoods_returnsListInstance() {
        String phrase = "";

        List<Food> result = repository.getFoods(phrase);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getFoods_returnsListWithFoods() {
        List<Food> foods = List.of(new Food(), new Food());
        String phrase = "";

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(FoodRowMapper.class), Mockito.anyString())).thenReturn(foods);

        List<Food> result = repository.getFoods(phrase);

        Assertions.assertEquals(foods.size(), result.size());
    }

    @Test
    public void getFoodsPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;

        List<Food> result = repository.getFoodsPagination(start, limit);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getFoodsPagination_returnsListWithFoods() {
        List<Food> foods = List.of(new Food(), new Food());
        Integer start = 2;
        Integer limit = 2;

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(FoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(foods);

        List<Food> result = repository.getFoodsPagination(start, limit);
        Assertions.assertEquals(foods.size(), result.size());
    }

    @Test
    public void getFoodById_returnsFoodInstance() {
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(FoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(new Food());

        Food result = repository.getFoodById("");
        Assertions.assertInstanceOf(Food.class, result);
    }

    @Test
    public void getFoodById_returnsListWithFoods() {
        Food food = new Food();
        String foodId = "";

        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(FoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(food);

        Food result = repository.getFoodById(foodId);
        Assertions.assertEquals(food, result);
    }

    @Test
    public void getFoodsByName_returnsListInstance() {
        String name = "";

        List<Food> result = repository.getFoodsByName(name);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getFoodsByName_returnsListWithFoods() {
        List<Food> foods = List.of(new Food(), new Food());

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(FoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(foods);

        List<Food> result = repository.getFoodsByName("name");
        Assertions.assertEquals(foods.size(), result.size());
    }

    @Test
    public void addFood_returnsStringInstance() {
        Food newFood = new Food();

        String result = repository.addFood(newFood);
        Assertions.assertInstanceOf(String.class, result);
    }

    @Test
    public void addFood_returnsFoodId() {
        Food newFood = new Food();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(1);

        String result = repository.addFood(newFood);
        Assertions.assertNotNull(result);
    }
}