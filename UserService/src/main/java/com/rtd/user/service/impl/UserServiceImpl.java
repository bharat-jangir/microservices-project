package com.rtd.user.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rtd.user.service.entities.User;
import com.rtd.user.service.exceptions.ResourceNotFoundException;
import com.rtd.user.service.repositories.UserRepository;
import com.rtd.user.service.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
      return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User With Given Id Is NOt Found:"+userId));
    }

}
