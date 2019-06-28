package com.programming.exercise.synthesis.service;

import com.programming.exercise.synthesis.entity.Payment;

import java.util.Optional;

public interface PaymentService {
    Payment makePayment(Payment payment);
    Optional<Payment> getPayment(Integer paymentId);
}
