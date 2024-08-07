package org.dmle.userfood.controller;

import org.dmle.userfood.domain.ResponseEntity;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;

class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUsers_returnsResponseEntityInstance() {
        ResponseEntity<List<User>> result = controller.getUsers();
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUsers_returnsResponseEntityOKStatus() {
        ResponseEntity<List<User>> result = controller.getUsers();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUsers_returnsResponseEntityWithUsers() {
        List<User> users = List.of(new User(), new User());

        Mockito.when(userService.getUsers()).thenReturn(users);

        ResponseEntity<List<User>> result = controller.getUsers();
        Assertions.assertEquals(users.size(), result.getData().size());
    }

    @Test
    public void getUsersPagination_returnsResponseEntityInstance() {
        Integer limit = 2;
        Integer page = 1;

        ResponseEntity<List<User>> result = controller.getUsersPagination(limit, page);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUsersPagination_returnsResponseEntityOKStatus() {
        Integer limit = 2;
        Integer page = 1;

        ResponseEntity<List<User>> result = controller.getUsersPagination(limit, page);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUsersPagination_returnsResponseEntityWithUsers() {
        List<User> users = List.of(new User(), new User());
        Integer start = 2;
        Integer limit = 2;
        Integer page = 1;

        Mockito.when(userService.getUsersPagination(start, limit)).thenReturn(users);

        ResponseEntity<List<User>> result = controller.getUsersPagination(limit, page);
        Assertions.assertEquals(users.size(), result.getData().size());
    }

    @Test
    public void getUserById_returnsResponseEntityInstance() {
        String userId = "";

        ResponseEntity<User> result = controller.getUserById(userId);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUserById_returnsResponseEntityOKStatus() {
        String userId = "";

        ResponseEntity<User> result = controller.getUserById(userId);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUserById_returnsResponseEntityWithUsers() {
        User user = new User();
        String userId = "";

        Mockito.when(userService.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> result = controller.getUserById(userId);
        Assertions.assertEquals(user, result.getData());
    }

    @Test
    public void addUser_returnsResponseEntityInstance() {
        ResponseEntity<String> result = controller.addUser(Mockito.anyMap());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void addUser_returnsResponseEntityOKStatus() {
        ResponseEntity<String> result = controller.addUser(Mockito.anyMap());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void addUser_returnsResponseEntityWithId() {
        String userId = "12054-14525-9535";

        Mockito.when(userService.addUser(Mockito.anyMap())).thenReturn(userId);

        ResponseEntity<String> result = controller.addUser(Mockito.anyMap());
        Assertions.assertEquals(userId, result.getData());
    }

    @Test
    public void updateUser_returnsResponseEntityInstance() {
        ResponseEntity<Boolean> result = controller.updateUser(Mockito.anyString(), Mockito.anyMap());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void updateUser_returnsResponseEntityOKStatus() {
        ResponseEntity<Boolean> result = controller.updateUser(Mockito.anyString(), Mockito.anyMap());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateUser_returnsResponseEntityWithTrue() {
        Boolean status = true;

        Mockito.when(userService.updateUser(Mockito.anyString(), Mockito.anyMap())).thenReturn(status);

        ResponseEntity<Boolean> result = controller.updateUser(Mockito.anyString(), Mockito.anyMap());
        Assertions.assertEquals(status, result.getData());
    }

    @Test
    public void deleteUser_returnsResponseEntityInstance() {
        ResponseEntity<Boolean> result = controller.deleteUser(Mockito.anyString());
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void deleteUser_returnsResponseEntityOKStatus() {
        ResponseEntity<Boolean> result = controller.deleteUser(Mockito.anyString());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void deleteUser_returnsResponseEntityWithTrue() {
        Boolean status = true;

        Mockito.when(userService.deleteUser(Mockito.anyString())).thenReturn(status);

        ResponseEntity<Boolean> result = controller.deleteUser(Mockito.anyString());
        Assertions.assertEquals(status, result.getData());
    }
}