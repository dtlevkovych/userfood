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
    public void getUser_returnsResponseEntityInstance() {
        String userId = "";

        ResponseEntity<List<User>> result = controller.getUser(userId);
        Assertions.assertInstanceOf(ResponseEntity.class, result);
    }

    @Test
    public void getUser_returnsResponseEntityOKStatus() {
        String userId = "";

        ResponseEntity<List<User>> result = controller.getUser(userId);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getUser_returnsResponseEntityWithUsers() {
        List<User> user = List.of(new User());
        String userId = "";

        Mockito.when(userService.getUser(userId)).thenReturn(user);

        ResponseEntity<List<User>> result = controller.getUser(userId);
        Assertions.assertEquals(user.size(), result.getData().size());
    }
}