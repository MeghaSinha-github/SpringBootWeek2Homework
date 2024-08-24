package com.springbootweek2hw.springbootweek2hw.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    public boolean patternAnalyzer(String regex, String password){
    Pattern patterMatcher = Pattern.compile(regex);
    Matcher hasMatcher = patterMatcher.matcher(password);

        return hasMatcher.find();
    }
@Override
public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
    if(password==null)
        return false;
      //  throw new ResourceNotFoundException("Password Cannot be Null");

    if (password.length() < 10)
        return false;

    if(patternAnalyzer("[A-Z]",password) && patternAnalyzer("[a-z]",password) && patternAnalyzer("[^a-zA-Z0-9]",password))
        return true;

    return false;
}

}
