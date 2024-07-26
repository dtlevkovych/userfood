package org.dmle.userfood.repo;

import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

class UserRepositoryTest {

    @InjectMocks
    private UserRepositoryImpl repository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUsers_returnsListInstance() {
        List<User> result = repository.getUsers();
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUsers_returnsListWithUsers() {
        List<User> users = List.of(new User(), new User());

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(UserRowMapper.class))).thenReturn(users);

        List<User> result = repository.getUsers();

        Assertions.assertEquals(users.size(), result.size());
    }

    @Test
    public void getUsersPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;

        List<User> result = repository.getUsersPagination(start, limit);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUsersPagination_returnsListWithUsers() {
        List<User> users = List.of(new User(), new User());
        Integer start = 2;
        Integer limit = 2;

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(UserRowMapper.class))).thenReturn(users);

        List<User> result = repository.getUsersPagination(start, limit);
        Assertions.assertEquals(users.size(), result.size());
    }
    @Test
    public void getUser_returnsListInstance() {
        String userId = "";

        List<User> result = repository.getUser(userId);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUser_returnsListWithUsers() {
        List<User> users = List.of(new User());
        String userId = "";

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(UserRowMapper.class))).thenReturn(users);

        List<User> result = repository.getUser(userId);
        Assertions.assertEquals(users.size(), result.size());
    }
}