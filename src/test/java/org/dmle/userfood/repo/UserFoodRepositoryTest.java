package org.dmle.userfood.repo;

import org.dmle.userfood.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserFoodRepositoryTest {

    @InjectMocks
    private UserFoodRepositoryImpl repository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserFoods_returnsListInstance() {
        List<UserFood> result = repository.getUserFoods();
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUsers_returnsListWithUserFoods() {
        List<UserFood> userFoods = List.of(new UserFood(), new UserFood());

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(UserFoodRowMapper.class))).thenReturn(userFoods);

        List<UserFood> result = repository.getUserFoods();

        Assertions.assertEquals(userFoods.size(), result.size());
    }

    @Test
    public void getUserFoodsByUserId_returnsListInstance() {
        List<UserFood> userFood = List.of(new UserFood(), new UserFood());

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(UserFoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(userFood);

        List<UserFood> result = repository.getUserFoodsByUserId("");
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUserFoodsByUserId_returnsUser() {
        List<UserFood> userFood = List.of(new UserFood(), new UserFood());
        String userId = "";

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(UserFoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(userFood);

        List<UserFood> result = repository.getUserFoodsByUserId(userId);
        Assertions.assertEquals(userFood, result);
    }

    @Test
    public void getUserFoodsByUserIdPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;
        String userId = "6578276";

        List<UserFood> result = repository.getUserFoodsByUserIdPagination(start, limit, userId);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUserFoodsByUserIdPagination_returnsListWithUserFoods() {
        List<UserFood> userFoods = List.of(new UserFood(), new UserFood());
        Integer start = 2;
        Integer limit = 2;
        String userId = "6578276";

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(UserFoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(userFoods);

        List<UserFood> result = repository.getUserFoodsByUserIdPagination(start, limit, userId);
        Assertions.assertEquals(userFoods.size(), result.size());
    }

    @Test
    public void getUserFoodById_returnsUserFoodInstance() {
        UserFood userFood = new UserFood();
        String userId = "87634534";
        String foodId = "80976343";

        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(UserFoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(userFood);

        UserFood result = repository.getUserFoodById(userId, foodId);
        Assertions.assertInstanceOf(UserFood.class, result);
    }

    @Test
    public void getUserFoodById_returnsUserFood() {
        UserFood userFood = new UserFood();
        String userId = "87634534";
        String foodId = "80976343";

        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(UserFoodRowMapper.class), Mockito.any(Object[].class))).thenReturn(userFood);

        UserFood result = repository.getUserFoodById(userId, foodId);
        Assertions.assertEquals(userFood, result);
    }

    @Test
    public void getEatingHealthReport_returnsListInstance() {
        String userId = "675327";

        List<RateReport> result = repository.getEatingHealthReport(userId);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getEatingHealthReport_returnsListWithRateReports() {
        List<RateReport> rateReports = List.of(new RateReport(), new RateReport());
        String userId = "675327";

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RateReportRowMapper.class), Mockito.anyString())).thenReturn(rateReports);

        List<RateReport> result = repository.getEatingHealthReport(userId);

        Assertions.assertEquals(rateReports.size(), result.size());
    }
}
