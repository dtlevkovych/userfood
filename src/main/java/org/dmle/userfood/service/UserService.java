package org.dmle.userfood.service;

import org.dmle.userfood.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUsers();
    List<User> getUsersPagination(Integer start, Integer limit);
    List<User> getUser(String userId);
    String addUser(Map<String, Object> newUser);
}
