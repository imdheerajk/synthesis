package com.programming.exercise.synthesis.controller;

import com.programming.exercise.synthesis.entity.Payment;
import com.programming.exercise.synthesis.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * Author: Dheeraj
 *
 * Payment controller
 */

@RestController
public class PaymentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    PaymentService paymentService;

    @GetMapping(path = "/payment/{paymentId}")
    public @ResponseBody
    Payment getPayment(@PathVariable Integer paymentId) {
        Optional<Payment> payment = paymentService.getPayment(paymentId);
        return payment.get();
    }

    @PostMapping(path = "/payment")
    public @ResponseBody
    ResponseEntity<Object> makePayment(@Valid @RequestBody Payment payment) {

        //following logs will be masked for security purpose

        LOGGER.info("credit card=" + payment.getCreditCard());
        LOGGER.info("cvv=" + payment.getCvv());
        paymentService.makePayment(payment);

        //returning link of new resource for reference

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{paymentId}").buildAndExpand(payment.getPaymentId()).toUri();

        //setting Httpstatus 201 created for successful

        return ResponseEntity.created(location).build();
    }
}
