package org.dmle.userfood.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import org.dmle.userfood.domain.User;
import org.dmle.userfood.domain.UserDTO;
import org.dmle.userfood.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperService mapperService;

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
    public String addUser(UserDTO newUser) {
        return userRepository.addUser(validateUser(newUser));
    }

    @Override
    public Boolean updateUser(String userId, UserDTO userDTO) {
        return userRepository.updateUser(userId, validateUser(userId, userDTO));
    }

    private User validateUser(UserDTO userDTO) {
        return validateUser(null,userDTO);
    }

    private User validateUser(String userId, UserDTO userDTO) {

        User user = Objects.nonNull(userId)
                ? Optional.of(userRepository.getUserById(userId)).orElseThrow(() -> new IllegalArgumentException("User not found"))
                : new User();

        mapperService.updateValues(user, userDTO);

        if (StringUtils.isBlank(user.getFirstName())) {
            throw new IllegalArgumentException("First Name");
        }

        if (StringUtils.isBlank(user.getLastName())) {
            throw new IllegalArgumentException("Last Name");
        }

        if (Objects.isNull(user.getDob())) {
            throw new IllegalArgumentException("DOB");
        }

        List<User> existingUsers = userRepository.getUsersByName(user.getFirstName(), user.getLastName());

        if (!existingUsers.isEmpty()) {
            for (User existingUser : existingUsers) {
                if (!Objects.equals(existingUser.getId(), user.getId())) {
                    throw new IllegalArgumentException("User with such first and last name allready exist");
                }
            }
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
