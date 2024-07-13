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
import org.springframework.http.ResponseEntity;

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
        List<User> users = List.of(new User(), new User());

        Mockito.when(userService.getUsers()).thenReturn(users);

        ResponseEntity<List<User>> result = controller.getUsers();
        Assertions.assertEquals(users.size(), result.getBody().size());
    }
}