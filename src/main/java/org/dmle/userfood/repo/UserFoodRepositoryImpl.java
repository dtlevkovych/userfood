package org.dmle.userfood.repo;

import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.domain.UserFoodRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserFoodRepositoryImpl implements UserFoodRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserFood> getUserFoods() {
        String sql = "select * from user_food";
        try {
            return jdbcTemplate.query(sql, new UserFoodRowMapper());
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }
}
