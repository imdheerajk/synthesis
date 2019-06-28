package com.programming.exercise.synthesis.service;

import com.programming.exercise.synthesis.entity.User;
import com.programming.exercise.synthesis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> getUser(Integer id) {
         return userRepository.findById(id);
    }

    @Override
    public User makeUser(User user) {
        return userRepository.save(user);
    }
}
