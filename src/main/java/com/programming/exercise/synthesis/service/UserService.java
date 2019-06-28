package com.programming.exercise.synthesis.service;

import com.programming.exercise.synthesis.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(Integer id);
    User  makeUser(User user);
}
