package org.dmle.userfood.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFoodRowMapper implements RowMapper<UserFood> {
    @Override
    public UserFood mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserFood userFood = new UserFood();
        userFood.setId(rs.getString("id"));
        userFood.setUserId(rs.getString("user_id"));
        userFood.setFoodId(rs.getString("food_id"));
        userFood.setName(rs.getString("name"));
        userFood.setRateId(rs.getString("rate_id"));

        return userFood;
    }
}
