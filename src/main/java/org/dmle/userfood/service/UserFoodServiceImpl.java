package org.dmle.userfood.service;

import org.dmle.userfood.domain.UserFood;
import org.dmle.userfood.repo.UserFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFoodServiceImpl implements UserFoodService{

    @Autowired
    private UserFoodRepository userFoodRepository;

    @Override
    public List<UserFood> getUserFoods() {
        return userFoodRepository.getUserFoods();
    }
}
