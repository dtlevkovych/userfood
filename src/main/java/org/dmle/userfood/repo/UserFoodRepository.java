package org.dmle.userfood.repo;

import org.dmle.userfood.domain.RateReport;
import org.dmle.userfood.domain.UserFood;

import java.util.List;

public interface UserFoodRepository {

    List<UserFood> getUserFoods();
    List<UserFood> getUserFoodsByUserId(String userId);
    List<UserFood> getUserFoodsByUserIdPagination(Integer start, Integer limit, String userId);
    UserFood getUserFoodByUserAndFoodId(String userId, String foodId);
    List<RateReport> getEatingHealthReport(String userId);
    UserFood getUserFoodById(String userFoodId);
    String addUserFood(UserFood newUserFood);
    Boolean deleteUserFoodByUserId(String userId);
    Boolean deleteUserFood(String userFoodId);
}
