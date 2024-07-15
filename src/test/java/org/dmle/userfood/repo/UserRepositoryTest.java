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
}