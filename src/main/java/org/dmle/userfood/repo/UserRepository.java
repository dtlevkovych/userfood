package org.dmle.userfood.repo;

import org.dmle.userfood.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();
}
