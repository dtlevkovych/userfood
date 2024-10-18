package org.dmle.userfood.controller;

import org.dmle.userfood.domain.RateReport;
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
    public void getUserFoodByUserAndFoodId_returnsResponseEntityInstance() {
        String userId = "536382";
        String foodId = "92835";

        ResponseEntity<UserFood> result = controller.getUserFoodByUserAndFoodId(userId, foodId);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUserFoodByUserAndFoodId_returnsResponseEntityOKStatus() {
        String userId = "536382";
        String foodId = "92835";

        ResponseEntity<UserFood> result = controller.getUserFoodByUserAndFoodId(userId, foodId);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUserFoodByUserAndFoodId_returnsResponseEntityWithUserFood() {
        UserFood userFood = new UserFood();
        String userId = "536382";
        String foodId = "92835";

        Mockito.when(userFoodService.getUserFoodByUserAndFoodId(userId, foodId)).thenReturn(userFood);

        ResponseEntity<UserFood> result = controller.getUserFoodByUserAndFoodId(userId, foodId);
        Assertions.assertEquals(userFood, result.getData());
    }

    @Test
    public void getEatingHealthReport_returnsResponseEntityInstance() {
        String userId = "9742247";

        ResponseEntity<List<RateReport>> result = controller.getEatingHealthReport(userId);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getEatingHealthReport_returnsResponseEntityOKStatus() {
        String userId = "9742247";

        ResponseEntity<List<RateReport>> result = controller.getEatingHealthReport(userId);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getEatingHealthReport_returnsResponseEntityWithRateReport() {
        List<RateReport> rateReports = List.of(new RateReport(), new RateReport());
        String userId = "9742247";

        Mockito.when(userFoodService.getEatingHealthReport(Mockito.anyString())).thenReturn(rateReports);

        ResponseEntity<List<RateReport>> result = controller.getEatingHealthReport(userId);
        Assertions.assertEquals(rateReports.size(), result.getData().size());
    }

    @Test
    public void addUserFood_returnsResponseEntityInstance() {
        ResponseEntity<String> result = controller.addUserFood(Mockito.any());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void addUserFood_returnsResponseEntityOKStatus() {
        ResponseEntity<String> result = controller.addUserFood(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void addUserFood_returnsResponseEntityWithId() {
        String userFoodId = "12054-14525-9535";

        Mockito.when(userFoodService.addUserFood(Mockito.any())).thenReturn(userFoodId);

        ResponseEntity<String> result = controller.addUserFood(Mockito.any());
        Assertions.assertEquals(userFoodId, result.getData());
    }

    @Test
    public void deleteUserFood_returnsResponseEntityInstance() {
        ResponseEntity<Boolean> result = controller.deleteUserFood(Mockito.anyString());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void deleteUserFood_returnsResponseEntityOKStatus() {
        ResponseEntity<Boolean> result = controller.deleteUserFood(Mockito.anyString());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void deleteUserFood_returnsResponseEntityWithTrue() {
        Boolean status = true;

        Mockito.when(userFoodService.deleteUserFood(Mockito.anyString())).thenReturn(status);

        ResponseEntity<Boolean> result = controller.deleteUserFood(Mockito.anyString());
        Assertions.assertEquals(status, result.getData());
    }
}
