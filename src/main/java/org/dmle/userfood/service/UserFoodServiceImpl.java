package org.dmle.userfood.service;

import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.dmle.userfood.domain.RateReport;
import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.repo.FoodRepositoryImpl;
import org.dmle.userfood.repo.UserFoodRepository;
import org.dmle.userfood.repo.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserFoodServiceImpl implements UserFoodService{

    public static final String USER_ID = "userId";
    public static final String FOOD_ID = "foodId";

    @Autowired
    private UserFoodRepository userFoodRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private FoodRepositoryImpl foodRepository;

    @Override
    public List<UserFood> getUserFoods() {
        return userFoodRepository.getUserFoods();
    }

    @Override
    public List<UserFood> getUserFoodsByUserId(String userId) {
        return userFoodRepository.getUserFoodsByUserId(userId);
    }

    @Override
    public List<UserFood> getUserFoodsByUserIdPagination(Integer start, Integer limit, String userId) {
        return userFoodRepository.getUserFoodsByUserIdPagination(start, limit, userId);
    }

    @Override
    public UserFood getUserFoodByUserAndFoodId(String userId, String foodId) {
        return userFoodRepository.getUserFoodByUserAndFoodId(userId, foodId);
    }

    @Override
    public List<RateReport> getEatingHealthReport(String userId) {
        return userFoodRepository.getEatingHealthReport(userId);
    }

    @Override
    public String addUserFood(Map<String, Object> newUserFood) {
        return userFoodRepository.addUserFood(validateUserFood(newUserFood));
    }

    private UserFood validateUserFood(Map<String, Object> userFoodMap) {
        UserFood userFood = new UserFood();

        userFood.setUserId(MapUtils.getString(userFoodMap, USER_ID));
        if (StringUtils.isBlank(userFood.getUserId())) {
            throw new IllegalArgumentException("User id");
        }

        userFood.setFoodId(MapUtils.getString(userFoodMap, FOOD_ID));
        if (StringUtils.isBlank(userFood.getFoodId())) {
            throw new IllegalArgumentException("Food id");
        }

        if (userRepository.getUserById(userFood.getUserId()) == null) {
            throw new IllegalArgumentException("Wrong user id");
        }

        if (foodRepository.getFoodById(userFood.getFoodId()) == null) {
            throw new IllegalArgumentException("Wrong food id");
        }

        if (userFoodRepository.getUserFoodByUserAndFoodId(userFood.getUserId(), userFood.getFoodId()) != null) {
            throw new IllegalArgumentException("Food already exist for the user");
        }

        return userFood;
    }

    @Override
    public Boolean deleteUserFoodByUserId(String userId) {
        if (userFoodRepository.deleteUserFoodByUserId(userId) == null) {
            throw new IllegalArgumentException("Not Found");
        }

        return userFoodRepository.deleteUserFoodByUserId(userId);
    }

    @Override
    public Boolean deleteUserFood(String userFoodId) {
        if (userFoodRepository.getUserFoodById(userFoodId) == null) {
            throw new IllegalArgumentException("Not Found");
        }

        return userFoodRepository.deleteUserFood(userFoodId);
    }
}
