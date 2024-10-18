package org.dmle.userfood.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFoodDTO {
    String userId;
    String foodId;
    String name;
    String rateId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    @Override
    public String toString() {
        return "UserFood{" + "userId='" + userId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", name='" + name + '\'' +
                ", rateId='" + rateId + '\'' +
                '}';
    }
}
