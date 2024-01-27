package com.example.terrestrial_tutor.validators;
import com.example.terrestrial_tutor.annotations.PasswordMatches;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        RegistrationRequest userRegistrationRequest = (RegistrationRequest) obj;
        return userRegistrationRequest.getPassword().equals(userRegistrationRequest.getConfirmPassword());
    }
}
