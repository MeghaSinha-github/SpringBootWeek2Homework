package com.springbootweek2hw.springbootweek2hw.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Integer> {
    @Override
    public boolean isValid(Integer inputNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (inputNumber == null) {
            return false;
        }
        if (inputNumber <= 1) {
            return false;
        }
        for (int i = 2; i * i <= inputNumber; i++) {
            if (inputNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}
