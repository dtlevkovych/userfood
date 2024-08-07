package org.dmle.userfood.repo;

import org.dmle.userfood.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();
    List<User> getUsersPagination(Integer start, Integer limit);
    User getUserById(String userId);
    String addUser(User newUser);
    List<User> getUsersByName(String firstName, String lastName);
    Boolean updateUser(String id, User updateUser);
    Boolean deleteUser(String userId);
}
