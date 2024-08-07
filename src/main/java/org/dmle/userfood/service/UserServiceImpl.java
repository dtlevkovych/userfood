package org.dmle.userfood.service;

import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String DOB = "dob";


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public List<User> getUsersPagination(Integer start, Integer limit) {
        return userRepository.getUsersPagination(start, limit);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public String addUser(Map<String, Object> newUser) {
        return userRepository.addUser(validateUser(newUser));
    }

    @Override
    public Boolean updateUser(String userId, Map<String, Object> updateUser) {
        return userRepository.updateUser(userId, validateUser(updateUser));
    }

    private User validateUser(Map<String, Object> userMap) {
        User user = new User();

        user.setFirstName(MapUtils.getString(userMap, FIRST_NAME));
        if (StringUtils.isBlank(user.getFirstName())) {
            throw new IllegalArgumentException("First Name");
        }

        user.setLastName(MapUtils.getString(userMap, LAST_NAME));
        if (StringUtils.isBlank(user.getLastName())) {
            throw new IllegalArgumentException("Last Name");
        }

        user.setDob(MapUtils.getLong(userMap, DOB, 0L));
        if (user.getDob() == 0) {
            throw new IllegalArgumentException("DOB");
        }

        List<User> existingUsers = userRepository.getUsersByName(user.getFirstName(), user.getLastName());

        if (!existingUsers.isEmpty()) {
            throw new IllegalArgumentException("User with such first and last name allready exist");
        }

        return user;
    }

    @Override
    public Boolean deleteUser(String userId) {
        if (userRepository.getUserById(userId) == null) {
            throw new IllegalArgumentException("Not Found");
        }

        return userRepository.deleteUser(userId);
    }
}
