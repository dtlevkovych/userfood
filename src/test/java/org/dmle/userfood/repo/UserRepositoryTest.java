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

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(UserRowMapper.class), Mockito.any(Object[].class))).thenReturn(users);

        List<User> result = repository.getUsersPagination(start, limit);
        Assertions.assertEquals(users.size(), result.size());
    }

    @Test
    public void getUserById_returnsUserInstance() {
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(UserRowMapper.class), Mockito.any(Object[].class))).thenReturn(new User());

        User result = repository.getUserById("");
        Assertions.assertInstanceOf(User.class, result);
    }

    @Test
    public void getUserById_returnsListWithUsers() {
        User user = new User();
        String userId = "";

        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(UserRowMapper.class), Mockito.any(Object[].class))).thenReturn(user);

        User result = repository.getUserById(userId);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void getUsersByName_returnsListInstance() {
        String firstName = "";
        String lastName = "";

        List<User> result = repository.getUsersByName(firstName, lastName);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUsersByName_returnsListWithUser() {
        List<User> users = List.of(new User());

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(UserRowMapper.class), Mockito.any(Object[].class))).thenReturn(users);

        List<User> result = repository.getUsersByName("firstName", "lastName");
        Assertions.assertEquals(users.size(), result.size());
    }

    @Test
    public void addUser_returnsStringInstance() {
        User newUser = new User();

        String result = repository.addUser(newUser);
        Assertions.assertInstanceOf(String.class, result);
    }

    @Test
    public void addUser_returnsUserId() {
        User newUser = new User();

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(1);

        String result = repository.addUser(newUser);
        Assertions.assertNotNull(result);
    }

    @Test
    public void updateUser_returnsBooleanInstance() {
        Boolean result = repository.updateUser("", new User());
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void updateUser_returnsTrue() {
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyLong())).thenReturn(1);

        Boolean result = repository.updateUser("", new User());
        Assertions.assertEquals(result, true);
    }

    @Test
    public void deleteUser_returnsBooleanInstance() {
        Boolean result = repository.deleteUser("");
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void deleteUser_returnsTrue() {
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString())).thenReturn(1);

        Boolean result = repository.deleteUser("");
        Assertions.assertEquals(result, true);
    }
}