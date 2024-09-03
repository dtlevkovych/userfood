package org.dmle.userfood.controller;

import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.service.UserFoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UserFoodControllerTest {

    @InjectMocks
    private UserFoodController controller;

    @Mock
    private UserFoodService userFoodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserFoods_returnsResponseEntityInstance() {
        ResponseEntity<List<UserFood>> result = controller.getUserFoods();
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUserFoods_returnsResponseEntityOKStatus() {
        ResponseEntity<List<UserFood>> result = controller.getUserFoods();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUserFoods_returnsResponseEntityWithUserFoods() {
        List<UserFood> userFoods = List.of(new UserFood(), new UserFood());

        Mockito.when(userFoodService.getUserFoods()).thenReturn(userFoods);

        ResponseEntity<List<UserFood>> result = controller.getUserFoods();
        Assertions.assertEquals(userFoods.size(), result.getData().size());
    }

    @Test
    public void getUserFoodsByUserId_returnsResponseEntityInstance() {
        String userId = "";

        ResponseEntity<List<UserFood>> result = controller.getUserFoodsByUserId(userId);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUserFoodsByUserId_returnsResponseEntityOKStatus() {
        String userId = "";

        ResponseEntity<List<UserFood>> result = controller.getUserFoodsByUserId(userId);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUserFoodsByUserId_returnsResponseEntityWithUserFoods() {
        List<UserFood> userFood = List.of(new UserFood(), new UserFood());
        String userId = "";

        Mockito.when(userFoodService.getUserFoodsByUserId(userId)).thenReturn(userFood);

        ResponseEntity<List<UserFood>> result = controller.getUserFoodsByUserId(userId);
        Assertions.assertEquals(userFood, result.getData());
    }

    @Test
    public void getUserFoodsByUserIdPagination_returnsResponseEntityInstance() {
        Integer limit = 2;
        Integer page = 1;
        String userId = "122352";

        ResponseEntity<List<UserFood>> result = controller.getUserFoodsByUserIdPagination(userId, limit, page);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUserFoodsByUserIdPagination_returnsResponseEntityOKStatus() {
        Integer limit = 2;
        Integer page = 1;
        String userId = "122352";

        ResponseEntity<List<UserFood>> result = controller.getUserFoodsByUserIdPagination(userId, limit, page);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUserFoodsByUserIdPagination_returnsResponseEntityWithUserFoods() {
        List<UserFood> userFoods = List.of(new UserFood(), new UserFood());
        Integer start = 2;
        Integer limit = 2;
        Integer page = 1;
        String userId = "122352";

        Mockito.when(userFoodService.getUserFoodsByUserIdPagination(start, limit, userId)).thenReturn(userFoods);

        ResponseEntity<List<UserFood>> result = controller.getUserFoodsByUserIdPagination(userId, limit, page);
        Assertions.assertEquals(userFoods.size(), result.getData().size());
    }

    @Test
    public void getUserFoodById_returnsResponseEntityInstance() {
        String userId = "536382";
        String foodId = "92835";

        ResponseEntity<UserFood> result = controller.getUserFoodById(userId, foodId);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUserFoodById_returnsResponseEntityOKStatus() {
        String userId = "536382";
        String foodId = "92835";

        ResponseEntity<UserFood> result = controller.getUserFoodById(userId, foodId);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUserFoodById_returnsResponseEntityWithUserFood() {
        UserFood userFood = new UserFood();
        String userId = "536382";
        String foodId = "92835";

        Mockito.when(userFoodService.getUserFoodById(userId, foodId)).thenReturn(userFood);

        ResponseEntity<UserFood> result = controller.getUserFoodById(userId, foodId);
        Assertions.assertEquals(userFood, result.getData());
    }
}
