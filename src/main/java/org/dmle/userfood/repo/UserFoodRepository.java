package org.dmle.userfood.repo;

import org.dmle.userfood.domain.UserFood;

import java.util.List;

public interface UserFoodRepository {

    List<UserFood> getUserFoods();
    List<UserFood> getUserFoodsByUserId(String userId);
    List<UserFood> getUserFoodsByUserIdPagination(Integer start, Integer limit, String userId);
    UserFood getUserFoodById(String userId, String foodId);
}
