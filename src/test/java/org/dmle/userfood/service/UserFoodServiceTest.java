package org.dmle.userfood.service;

import org.dmle.userfood.domain.RateReport;
import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.repo.UserFoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserFoodServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserFoodServiceTest.class);

    @InjectMocks
    private UserFoodServiceImpl service;

    @Mock
    private UserFoodRepository userFoodRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserFoods_returnsListInstance() {
        List<UserFood> result = service.getUserFoods();
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUserFoods_returnsListWithUserFoods() {
        List<UserFood> userFoods = List.of(new UserFood(), new UserFood());

        Mockito.when(userFoodRepository.getUserFoods()).thenReturn(userFoods);

        List<UserFood> result = service.getUserFoods();
        Assertions.assertEquals(userFoods.size(), result.size());
    }

    @Test
    public void getUserFoodsByUserId_returnsListInstance() {
        List<UserFood> userFood = List.of(new UserFood(), new UserFood());
        String userId = "";

        Mockito.when(userFoodRepository.getUserFoodsByUserId(userId)).thenReturn(userFood);

        List<UserFood> result = service.getUserFoodsByUserId(userId);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUserFoodsByUserId_returnsListWithUserFoods() {
        List<UserFood> userFood = List.of(new UserFood(), new UserFood());

        Mockito.when(userFoodRepository.getUserFoodsByUserId(Mockito.anyString())).thenReturn(userFood);

        List<UserFood> result = service.getUserFoodsByUserId("");
        Assertions.assertEquals(userFood, result);
    }

    @Test
    public void getUserFoodsByUserIdPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;
        String userId = "534276";

        List<UserFood> result = service.getUserFoodsByUserIdPagination(start, limit, userId);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUserFoodsByUserIdPagination_returnsListWithUserFoods() {
        List<UserFood> userFoods = List.of(new UserFood(), new UserFood());
        Integer start = 2;
        Integer limit = 2;
        String userId = "534276";

        Mockito.when(userFoodRepository.getUserFoodsByUserIdPagination(start, limit, userId)).thenReturn(userFoods);

        List<UserFood> result = service.getUserFoodsByUserIdPagination(start, limit, userId);
        Assertions.assertEquals(userFoods.size(), result.size());
    }

    @Test
    public void getUserFoodById_returnsUserFoodInstance() {
        List<UserFood> userFood = List.of(new UserFood(), new UserFood());
        String userId = "";

        Mockito.when(userFoodRepository.getUserFoodsByUserId(userId)).thenReturn(userFood);

        List<UserFood> result = service.getUserFoodsByUserId(userId);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUserFoodById_returnsListWithUserFood() {
        UserFood userFood = new UserFood();
        String userId = "874686745";
        String foodId = "874686745";

        Mockito.when(userFoodRepository.getUserFoodById(userId, foodId)).thenReturn(userFood);

        UserFood result = service.getUserFoodById(userId, foodId);
        Assertions.assertEquals(userFood, result);
    }

    @Test
    public void getEatingHealthReport_returnsListInstance() {
        String userId = "209573";

        List<RateReport> result = service.getEatingHealthReport(userId);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUsers_returnsListWithRateReports() {
        List<RateReport> rateReports = List.of(new RateReport(), new RateReport());
        String userId = "209573";

        Mockito.when(userFoodRepository.getEatingHealthReport(Mockito.anyString())).thenReturn(rateReports);

        List<RateReport> result = service.getEatingHealthReport(userId);
        Assertions.assertEquals(rateReports.size(), result.size());
    }
}
