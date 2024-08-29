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
}
