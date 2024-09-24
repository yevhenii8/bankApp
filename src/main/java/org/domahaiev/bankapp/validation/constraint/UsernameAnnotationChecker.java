package org.domahaiev.bankapp.validation.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.domahaiev.bankapp.validation.annotation.UsernameChecker;

import java.util.Optional;

public class UsernameAnnotationChecker implements ConstraintValidator<UsernameChecker, String> {

    private static final String TEMPLATE = "^[0-9a-fA-F]{6,12}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(el -> !el.isBlank())
                .map(username -> username.matches(TEMPLATE))
                .orElse(false);
    }
}
