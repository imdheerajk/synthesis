package com.programming.exercise.synthesis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Author: Dheeraj
 * Credit card validator class
 */

public class CVVValidator implements ConstraintValidator<CVVConstraint,String> {

    @Override
    public boolean isValid(String creditCardNumber, ConstraintValidatorContext constraintValidatorContext) {
        return  creditCardNumber!= null && creditCardNumber.matches("[0-9]+")
                && (creditCardNumber.length() ==3);
    }
}
