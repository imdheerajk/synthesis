package com.programming.exercise.synthesis.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
/**
 * Author: Dheeraj
 * cvv validator annotation constraint
 */

@Documented
@Constraint(validatedBy = CVVValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public  @interface CVVConstraint {

    String message() default "Invalid  CVV number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
