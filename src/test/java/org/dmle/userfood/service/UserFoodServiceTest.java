package org.dmle.userfood.service;

import org.dmle.userfood.domain.*;
import org.dmle.userfood.repo.*;
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

    @Mock
    private UserRepositoryImpl userRepository;

    @Mock
    private FoodRepositoryImpl foodRepository;

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
    public void getUserFoodByUserAndFoodId_returnsUserFoodInstance() {
        UserFood userFood = new UserFood();
        String userId = "874686745";
        String foodId = "874686745";

        Mockito.when(userFoodRepository.getUserFoodByUserAndFoodId(userId, foodId)).thenReturn(userFood);

        UserFood result = service.getUserFoodByUserAndFoodId(userId, foodId);
        Assertions.assertInstanceOf(UserFood.class, result);
    }

    @Test
    public void getUserFoodByUserAndFoodId_returnsListWithUserFood() {
        UserFood userFood = new UserFood();
        String userId = "874686745";
        String foodId = "874686745";

        Mockito.when(userFoodRepository.getUserFoodByUserAndFoodId(userId, foodId)).thenReturn(userFood);

        UserFood result = service.getUserFoodByUserAndFoodId(userId, foodId);
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

    @Test
    public void addUserFood_validUserFood_returnsNewUserFoodId() {
        String userFoodId = "123-23323123";

        UserFoodDTO userFoodDTO = new UserFoodDTO();
        userFoodDTO.setUserId("Some User id");
        userFoodDTO.setFoodId("Some Food id");

        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(new User());
        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(new Food());

        Mockito.when(userFoodRepository.getUserFoodByUserAndFoodId(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Mockito.when(userFoodRepository.addUserFood(Mockito.any())).thenReturn(userFoodId);

        String newUserFoodId = service.addUserFood(userFoodDTO);

        Assertions.assertEquals(userFoodId, newUserFoodId);
    }

    @Test
    public void addUserFood_nonValidUserId_throwsException() {
        String userFoodId = "123-23323123";

        UserFoodDTO userFoodDTO = new UserFoodDTO();
        userFoodDTO.setFoodId("Some Food id");

        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(null);
        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(new Food());

        Mockito.when(userFoodRepository.getUserFoodByUserAndFoodId(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Mockito.when(userFoodRepository.addUserFood(Mockito.any())).thenReturn(userFoodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addUserFood(userFoodDTO));
    }

    @Test
    public void addUserFood_nonValidFoodId_throwsException() {
        String userFoodId = "123-23323123";

        UserFoodDTO userFoodDTO = new UserFoodDTO();
        userFoodDTO.setUserId("Some User id");

        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(new User());
        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(null);

        Mockito.when(userFoodRepository.getUserFoodByUserAndFoodId(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Mockito.when(userFoodRepository.addUserFood(Mockito.any())).thenReturn(userFoodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addUserFood(userFoodDTO));
    }

    @Test
    public void addUserFood_validUserFoodNotExist_returnsUserFoodId() {
        String userFoodId = "123-23323123";

        UserFoodDTO userFoodDTO = new UserFoodDTO();
        userFoodDTO.setUserId("Some User id");
        userFoodDTO.setFoodId("Some Food id");

        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(new User());
        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(new Food());

        Mockito.when(userFoodRepository.getUserFoodByUserAndFoodId(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Mockito.when(userFoodRepository.addUserFood(Mockito.any())).thenReturn(userFoodId);

        String newUserFoodId = service.addUserFood(userFoodDTO);

        Assertions.assertEquals(newUserFoodId, userFoodId);
    }

    @Test
    public void addUserFood_nonValidUserFoodExist_throwsException() {
        String userFoodId = "123-23323123";

        UserFoodDTO userFoodDTO = new UserFoodDTO();
        userFoodDTO.setUserId("Some User id");
        userFoodDTO.setUserId("Some Food id");

        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(new User());
        Mockito.when(foodRepository.getFoodById(Mockito.anyString())).thenReturn(new Food());

        Mockito.when(userFoodRepository.getUserFoodByUserAndFoodId(Mockito.anyString(), Mockito.anyString())).thenReturn(new UserFood());
        Mockito.when(userFoodRepository.addUserFood(Mockito.any())).thenReturn(userFoodId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addUserFood(userFoodDTO));
    }

    @Test
    public void deleteUserFoodByUserId_returnsBooleanInstance() {
        String userId = "82749827";

        Boolean result = service.deleteUserFoodByUserId(userId);
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void deleteUserFoodByUserId_existUser_returnsTrue() {
        String userId = "82749827";
        Boolean status = true;

        Mockito.when(userFoodRepository.deleteUserFoodByUserId(Mockito.anyString())).thenReturn(status);

        Boolean result = service.deleteUserFoodByUserId(userId);
        Assertions.assertEquals(result, status);
    }

    @Test
    public void deleteUserFood_returnsBooleanInstance() {
        Mockito.when(userFoodRepository.getUserFoodById(Mockito.anyString())).thenReturn(new UserFood());

        Boolean result = service.deleteUserFood(Mockito.anyString());
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void deleteUserFood_nonExistUser_throwsException() {
        String userId = "82749827";

        Mockito.when(userFoodRepository.getUserFoodById(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteUserFood(userId));
    }

    @Test
    public void deleteUserFood_existUser_returnsTrue() {
        String userId = "82749827";
        Boolean status = true;

        Mockito.when(userFoodRepository.getUserFoodById(Mockito.anyString())).thenReturn(new UserFood());
        Mockito.when(userFoodRepository.deleteUserFood(Mockito.anyString())).thenReturn(status);

        Boolean result = service.deleteUserFood(userId);
        Assertions.assertEquals(result, status);
    }
}