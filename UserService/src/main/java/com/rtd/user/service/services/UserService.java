package com.rtd.user.service.services;

import java.util.List;

import com.rtd.user.service.entities.User;

public interface UserService {


    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

}
