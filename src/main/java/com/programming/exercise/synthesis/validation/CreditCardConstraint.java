package com.programming.exercise.synthesis.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
/**
 * Author: Dheeraj
 *
 * CC validator annotation constraint
 */

@Documented
@Constraint(validatedBy = CreditCardValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardConstraint {

    String message() default "Invalid  credit  card number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
