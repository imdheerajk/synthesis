package com.programming.exercise.synthesis.repository;

import com.programming.exercise.synthesis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Dheeraj
 *
 */

public interface UserRepository extends JpaRepository<User,Integer> {
}
