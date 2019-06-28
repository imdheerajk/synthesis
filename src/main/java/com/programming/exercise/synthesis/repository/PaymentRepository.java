package com.programming.exercise.synthesis.repository;

import com.programming.exercise.synthesis.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Author: Dheeraj
 *
 */
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
