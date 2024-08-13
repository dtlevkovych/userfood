package org.dmle.userfood.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodRowMapper implements RowMapper<Food> {
    @Override
    public Food mapRow(ResultSet rs, int rowNum) throws SQLException {

        Food food = new Food();
        food.setId(rs.getString("id"));
        food.setName(rs.getString("name"));
        food.setRateId(rs.getString("rate_id"));

        return food;
    }
}
