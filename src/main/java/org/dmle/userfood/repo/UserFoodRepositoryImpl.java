package org.dmle.userfood.repo;

import org.dmle.userfood.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class UserFoodRepositoryImpl implements UserFoodRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserFood> getUserFoods() {
        String sql = """
            select uf.*, f.name, f.rate_id 
            from user_food as uf 
            inner join food as f on uf.food_id=f.id
            """;
        try {
            return jdbcTemplate.query(sql, new UserFoodRowMapper());
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public List<UserFood> getUserFoodsByUserId(String userId) {
        String sql = """
                select uf.*, f.name, f.rate_id 
                from user_food as uf 
                inner join food as f on uf.food_id=f.id 
                where uf.user_id=?
                """;
        try {
            return jdbcTemplate.query(sql, new UserFoodRowMapper(), userId);
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }

    @Override
    public List<UserFood> getUserFoodsByUserIdPagination(Integer start, Integer limit, String userId) {
        String sql = """
            select uf.id, uf.user_id, uf.food_id, f.name, f.rate_id
            from user_food as uf
            inner join food as f on uf.food_id=f.id
            where uf.user_id=?
            order by f.name limit ?,?
            """;
        try {
            return jdbcTemplate.query(sql , new UserFoodRowMapper(), userId, start, limit);
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public UserFood getUserFoodByUserAndFoodId(String userId, String foodId) {
        String sql = """
                select uf.*, f.name, f.rate_id 
                from user_food as uf 
                inner join food as f on uf.food_id=f.id 
                where uf.user_id=? and uf.food_id=?
                """;
        try {
            return jdbcTemplate.queryForObject(sql, new UserFoodRowMapper(), userId, foodId);
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }

    @Override
    public List<RateReport> getEatingHealthReport(String userId) {
        String sql = """
            SELECT r.id, r.name, r.value, r.color_hex, r.created_at, count(*) as count
            FROM user_food AS uf
            INNER JOIN food AS f ON uf.food_id=f.id
            INNER JOIN rate AS r ON f.rate_id = r.id
            WHERE uf.user_id=?
            GROUP BY r.value
            ORDER BY r.value
            """;
        try {
            return jdbcTemplate.query(sql , new RateReportRowMapper(), userId);
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public UserFood getUserFoodById(String userFoodId) {
        String sql = """
            select uf.id, uf.user_id, uf.food_id, f.name, f.rate_id 
            from user_food as uf 
            inner join food as f on uf.food_id=f.id 
            where uf.id=? limit 0,1
            """;
        try {
            return jdbcTemplate.queryForObject(sql, new UserFoodRowMapper(), userFoodId);
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }

    @Override
    public String addUserFood(UserFood newUserFood) {
        String sql = "insert into user_food (id, user_id, food_id) values (?, ?, ?)";

        String id = String.valueOf(UUID.randomUUID());

        try {
            jdbcTemplate.update(sql, id, newUserFood.getUserId(), newUserFood.getFoodId());
            return id;
        } catch (DataAccessException ignored) {}
        return null;
    }

    @Override
    public Boolean deleteUserFoodByUserId(String userId) {
        String sql = "delete from user_food where user_id=?";

        try {
            jdbcTemplate.update(sql, userId);
            return true;
        } catch (DataAccessException ignored) {}
        return false;
    }

    @Override
    public Boolean deleteUserFood(String userFoodId) {
        String sql = "delete from user_food where id=?";

        try {
            jdbcTemplate.update(sql, userFoodId);
            return true;
        } catch (DataAccessException ignored) {}
        return false;
    }
}