package org.dmle.userfood.service;

import org.dmle.userfood.domain.User;
import org.dmle.userfood.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUsers_returnsListInstance() {
        List<User> result = service.getUsers();
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUsers_returnsListWithUsers() {
        List<User> users = List.of(new User(), new User());

        Mockito.when(userRepository.getUsers()).thenReturn(users);

        List<User> result = service.getUsers();
        Assertions.assertEquals(users.size(), result.size());
    }

    @Test
    public void getUsersPagination_returnsListInstance() {
        Integer start = 2;
        Integer limit = 2;

        List<User> result = service.getUsersPagination(start, limit);
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUsersPagination_returnsListWithUsers() {
        List<User> users = List.of(new User(), new User());
        Integer start = 2;
        Integer limit = 2;

        Mockito.when(userRepository.getUsersPagination(start, limit)).thenReturn(users);

        List<User> result = service.getUsersPagination(start, limit);
        Assertions.assertEquals(users.size(), result.size());
    }

    @Test
    public void getUser_returnsListInstance() {
        List<User> result = service.getUser(Mockito.anyString());
        Assertions.assertInstanceOf(List.class, result);
    }

    @Test
    public void getUser_returnsListWithUsers() {
        List<User> user = List.of(new User());

        Mockito.when(userRepository.getUser(Mockito.anyString())).thenReturn(user);

        List<User> result = service.getUser(Mockito.anyString());
        Assertions.assertEquals(user.size(), result.size());
    }


    @Test
    public void addUser_validUser_returnsNewUserId() {
        String userId = "123-23323123";

        Map<String, Object> userMap = new HashMap<>();
        userMap.put(UserServiceImpl.FIRST_NAME, "Some First Name");
        userMap.put(UserServiceImpl.LAST_NAME, "Some Last Name");
        userMap.put(UserServiceImpl.DOB, 1231231L);

        Mockito.when(userRepository.getUsersByName(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(userRepository.addUser(Mockito.any())).thenReturn(userId);

        String newUserId = service.addUser(userMap);

        Assertions.assertEquals(userId, newUserId);
    }


    @Test
    public void addUser_nonValidFirstName_throwsException() {
        String userId = "123-23323123";

        Map<String, Object> userMap = new HashMap<>();
        userMap.put(UserServiceImpl.LAST_NAME, "Some Last Name");
        userMap.put(UserServiceImpl.DOB, 1231231L);

        Mockito.when(userRepository.getUsersByName(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(userRepository.addUser(Mockito.any())).thenReturn(userId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addUser(userMap));
    }


    @Test
    public void addUser_nonValidLastName_throwsException() {
        String userId = "123-23323123";

        Map<String, Object> userMap = new HashMap<>();
        userMap.put(UserServiceImpl.FIRST_NAME, "Some First Name");
        userMap.put(UserServiceImpl.DOB, 1231231L);

        Mockito.when(userRepository.getUsersByName(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(userRepository.addUser(Mockito.any())).thenReturn(userId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addUser(userMap));
    }


    @Test
    public void addUser_nonValidDob_throwsException() {
        String userId = "123-23323123";

        Map<String, Object> userMap = new HashMap<>();
        userMap.put(UserServiceImpl.FIRST_NAME, "Some First Name");
        userMap.put(UserServiceImpl.LAST_NAME, "Some Last Name");

        Mockito.when(userRepository.getUsersByName(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(userRepository.addUser(Mockito.any())).thenReturn(userId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addUser(userMap));
    }


    @Test
    public void addUser_validUserNotExist_returnsUserId() {
        String userId = "123-23323123";

        Map<String, Object> userMap = new HashMap<>();
        userMap.put(UserServiceImpl.FIRST_NAME, "Some First Name");
        userMap.put(UserServiceImpl.LAST_NAME, "Some Last Name");
        userMap.put(UserServiceImpl.DOB, 1231231L);

        Mockito.when(userRepository.getUsersByName(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(userRepository.addUser(Mockito.any())).thenReturn(userId);

        String newUserId = service.addUser(userMap);

        Assertions.assertEquals(newUserId, userId);
    }


    @Test
    public void addUser_nonValidUserExist_throwsException() {
        String userId = "123-23323123";

        Map<String, Object> userMap = new HashMap<>();
        userMap.put(UserServiceImpl.FIRST_NAME, "Some First Name");
        userMap.put(UserServiceImpl.LAST_NAME, "Some Last Name");
        userMap.put(UserServiceImpl.DOB, 1231231L);

        Mockito.when(userRepository.getUsersByName(Mockito.anyString(), Mockito.anyString())).thenReturn(List.of(new User()));
        Mockito.when(userRepository.addUser(Mockito.any())).thenReturn(userId);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addUser(userMap));
    }
}