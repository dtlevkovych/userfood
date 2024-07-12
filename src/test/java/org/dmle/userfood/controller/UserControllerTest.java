package org.dmle.userfood.controller;

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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void getUsers_returnsResponseEntity() {
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
        User user1 = new User();
        User user2 = new User();
        List<User> users = List.of(user1, user2);

        Mockito.when(userService.getUsers()).thenReturn(users);

        ResponseEntity<List<User>> result = controller.getUsers();
        Assertions.assertEquals(users.size(), result.getBody().size());
    }
}