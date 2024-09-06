package org.dmle.userfood.service;

import org.dmle.userfood.domain.RateReport;
import org.dmle.userfood.domain.UserFood;

import java.util.List;
import java.util.Map;

public interface UserFoodService {

    List<UserFood> getUserFoods();
    List<UserFood> getUserFoodsByUserId(String userId);
    List<UserFood> getUserFoodsByUserIdPagination(Integer start, Integer limit, String userId);
    UserFood getUserFoodById(String userId, String foodId);
    List<RateReport> getEatingHealthReport(String userId);
    String addUserFood(Map<String, Object> newUserFood);
}
