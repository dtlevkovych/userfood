package org.dmle.userfood.service;

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
}
