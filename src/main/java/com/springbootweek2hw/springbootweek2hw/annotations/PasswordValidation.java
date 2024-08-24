package com.springbootweek2hw.springbootweek2hw.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordValidation {
    String message() default "Password should contain at a minimum the following pattern: a.One Upper Case b.One LowerCase c.One Special Character d.Minimum length 10 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}