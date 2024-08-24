package com.springbootweek2hw.springbootweek2hw.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {PrimeNumberValidator.class})
public @interface PrimeNumberValidation {
    String message() default "Number should only be prime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
