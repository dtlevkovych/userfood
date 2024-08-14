package org.dmle.userfood.repo;

import org.dmle.userfood.domain.Food;
import org.dmle.userfood.domain.FoodRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class FoodRepositoryImpl implements FoodRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Food> getFoods(String phrase) {
        String sql = "select * from food";

        System.out.println(phrase);

        String food_params = "";
        if (phrase != null) {
            food_params = ("%" + phrase + "%");
            sql += " where name like ?";
        }
        try {
            return jdbcTemplate.query(sql, new FoodRowMapper(), food_params);
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public List<Food> getFoodsPagination(Integer start, Integer limit) {
        String sql = "select * from food order by name limit ?,?";
        try {
            return jdbcTemplate.query(sql , new FoodRowMapper(), start, limit);
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public Food getFoodById(String foodId) {
        String sql = "select * from food where id=? limit 0,1";
        try {
            return jdbcTemplate.queryForObject(sql, new FoodRowMapper(), foodId);
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }

    @Override
    public List<Food> getFoodsByName(String name) {
        String sql = "select * from food where name=?";
        try {
            return jdbcTemplate.query(sql, new FoodRowMapper(), name);
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public String addFood(Food newFood) {
        String sql = "insert into food (id, name, rate_id) values (?, ?, ?)";

        String id = String.valueOf(UUID.randomUUID());

        try {
            jdbcTemplate.update(sql, id, newFood.getName(), newFood.getRateId());
            return id;
        } catch (DataAccessException ignored) {}
        return null;
    }

    @Override
    public Boolean updateFood(String foodId, Food updateFood) {
        String sql = "update food set name=?, rate_id=? where id=?";

        try {
            jdbcTemplate.update(sql, updateFood.getName(), updateFood.getRateId(), foodId);
            return true;
        } catch (DataAccessException ignored) {}
        return false;
    }
}
