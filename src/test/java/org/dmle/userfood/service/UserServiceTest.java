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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
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
    public void getUserById_returnsListInstance() {
        String userId = "";

        Mockito.when(userRepository.getUserById(userId)).thenReturn(new User());

        User result = service.getUserById(userId);
        Assertions.assertInstanceOf(User.class, result);
    }

    @Test
    public void getUserById_returnsListWithUsers() {
        User user = new User();

        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(user);

        User result = service.getUserById("");
        Assertions.assertEquals(user, result);
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

    @Test
    public void updateUser_returnsBooleanInstance() {
        String userId = "123-23323123";
        Boolean result = true;

        Map<String, Object> userMap = new HashMap<>();
        userMap.put(UserServiceImpl.FIRST_NAME, "Some First Name");
        userMap.put(UserServiceImpl.LAST_NAME, "Some Last Name");
        userMap.put(UserServiceImpl.DOB, 1231231L);

        Mockito.when(userRepository.getUsersByName(Mockito.anyString(), Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(userRepository.updateUser(Mockito.anyString(), Mockito.any())).thenReturn(result);

        Boolean serviceResult = service.updateUser(userId, userMap);

        Assertions.assertEquals(result, serviceResult);
    }

    @Test
    public void deleteUser_returnsBooleanInstance() {
        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(new User());

        Boolean result = service.deleteUser(Mockito.anyString());
        Assertions.assertInstanceOf(Boolean.class, result);
    }

    @Test
    public void deleteUser_nonExistUser_throwsException() {
        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteUser(Mockito.anyString()));
    }

    @Test
    public void deleteUser_existUser_returnsTrue() {
        Boolean status = true;

        Mockito.when(userRepository.getUserById(Mockito.anyString())).thenReturn(new User());
        Mockito.when(userRepository.deleteUser(Mockito.anyString())).thenReturn(status);

        Boolean result = service.deleteUser(Mockito.anyString());
        Assertions.assertEquals(result, status);
    }
}