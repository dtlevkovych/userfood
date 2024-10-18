package org.dmle.userfood.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodDTO {
    private String name;
    private String rateId;



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
        return "Food{" + "name='" + name + '\'' +
                ", rate=" + rateId +
                '}';
    }
}
