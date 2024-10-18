package org.dmle.userfood.service;

import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUsers();
    List<User> getUsersPagination(Integer start, Integer limit);
    User getUserById(String userId);
    String addUser(UserDTO newUser);
    Boolean updateUser(String userId, UserDTO updateUser);
    Boolean deleteUser(String userId);
}
