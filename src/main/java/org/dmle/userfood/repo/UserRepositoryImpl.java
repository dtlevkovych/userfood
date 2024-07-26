package org.dmle.userfood.repo;

import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUsers() {
        String sql = "select * from user";
        try {
            return jdbcTemplate.query(sql, new UserRowMapper());
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public List<User> getUsersPagination(Integer start, Integer limit) {
        String sql = "select * from user order by created_at desc limit ?,?";
        try {
           return jdbcTemplate.query(sql , new Object[] { start, limit }, new UserRowMapper());
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public List<User> getUser(String userId) {
        String sql = "select * from user where id=?";
        try {
            return jdbcTemplate.query(sql, new Object[] { userId }, new UserRowMapper());
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public List<User> getUsersByName(String firstName, String lastName) {
        String sql = "select * from user where first_name=? and last_name=?";
        try {
            return jdbcTemplate.query(sql, new Object[] { firstName, lastName }, new UserRowMapper());
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public String addUser(User newUser) {
        String sql = "insert into user (id, first_name, last_name, dob, created_at) values (?, ?, ?, ?, ?)";

        String id = String.valueOf(UUID.randomUUID());

        try {
            jdbcTemplate.update(sql, id, newUser.getFirstName(), newUser.getLastName(), newUser.getDob(), System.currentTimeMillis());
            return id;
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }
}
