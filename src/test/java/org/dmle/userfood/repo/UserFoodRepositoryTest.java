package org.dmle.userfood.repo;

import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.domain.UserFoodRowMapper;
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
}
