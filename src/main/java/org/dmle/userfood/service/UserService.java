package org.dmle.userfood.service;

import org.dmle.userfood.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUsers();
    List<User> getUsersPagination(Integer start, Integer limit);
    User getUserById(String userId);
    String addUser(Map<String, Object> newUser);
    Boolean updateUser(String userId, Map<String, Object> updateUser);
    Boolean deleteUser(String userId);
}
