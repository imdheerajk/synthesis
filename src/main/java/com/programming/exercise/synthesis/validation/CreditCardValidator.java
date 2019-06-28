package com.programming.exercise.synthesis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardValidator  implements ConstraintValidator<CreditCardConstraint,String> {

    @Override
    public boolean isValid(String creditCardNumber, ConstraintValidatorContext constraintValidatorContext) {

         return  creditCardNumber!= null && creditCardNumber.matches("[0-9]+")
                && (creditCardNumber.length() == 16);
    }
}
