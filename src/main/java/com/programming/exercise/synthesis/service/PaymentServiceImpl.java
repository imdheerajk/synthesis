package com.programming.exercise.synthesis.service;

import com.programming.exercise.synthesis.entity.Payment;
import com.programming.exercise.synthesis.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class PaymentServiceImpl implements  PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPayment(Integer paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
