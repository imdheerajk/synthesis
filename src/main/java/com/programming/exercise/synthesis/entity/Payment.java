package com.programming.exercise.synthesis.entity;

import com.programming.exercise.synthesis.validation.CVVConstraint;
import com.programming.exercise.synthesis.validation.CreditCardConstraint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 *
 * Author: Dheeraj
 * Payment API
 */

import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;
    @NotNull
    private String paymentAddress;

    @CreditCardConstraint
    private String creditCard;

     @CVVConstraint
    private String cvv;

    @NotNull
    @Future(message = "expiry date can not be past dated ")
    private Date expiryDate;



    public Payment() {
    }


    public Payment( Integer paymentId, String paymentAddress,  String creditCard,   String cvv, Date expiryDate) {
        this.paymentId=paymentId;
        this.paymentAddress = paymentAddress;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }



    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
