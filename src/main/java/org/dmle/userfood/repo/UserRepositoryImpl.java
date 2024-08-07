package org.dmle.userfood.repo;

import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
           return jdbcTemplate.query(sql , new UserRowMapper(), start, limit);
        } catch (EmptyResultDataAccessException ignored) {}
        return Collections.emptyList();
    }

    @Override
    public User getUserById(String userId) {
        String sql = "select * from user where id=? limit 0,1";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        } catch (EmptyResultDataAccessException ignored) {}
        return null;
    }

    @Override
    public List<User> getUsersByName(String firstName, String lastName) {
        String sql = "select * from user where first_name=? and last_name=?";
        try {
            return jdbcTemplate.query(sql, new UserRowMapper(), firstName, lastName);
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
        } catch (DataAccessException ignored) {}
        return null;
    }

    @Override
    public Boolean updateUser(String userId, User updateUser) {
        String sql = "update user set first_name=?, last_name=?, dob=? where id=?";

        try {
            jdbcTemplate.update(sql, updateUser.getFirstName(), updateUser.getLastName(), updateUser.getDob(), userId);
            return true;
        } catch (DataAccessException ignored) {}
        return false;
    }

    @Override
    public Boolean deleteUser(String userId) {
        String sql = "delete from user where id=?";

        try {
            jdbcTemplate.update(sql, userId);
            return true;
        } catch (DataAccessException ignored) {}
        return false;
    }
}
