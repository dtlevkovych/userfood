package org.dmle.userfood.domain;

public class UserFood {
    String id;
    String userId;
    String foodId;
    String name;
    String rateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        return "UserFood{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", name='" + name + '\'' +
                ", rateId='" + rateId + '\'' +
                '}';
    }
}
