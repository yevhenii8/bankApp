package org.domahaiev.bankapp.validation.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.domahaiev.bankapp.validation.annotation.SSNChecker;

import java.util.Optional;

public class SSNAnnotationChecker implements ConstraintValidator<SSNChecker, String> {

    private static final String TEMPLATE = "^[0-9]{3}-[0-9]{2}-[0-9]{4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(el -> !el.isBlank())
                .map(ssn -> ssn.matches(TEMPLATE))
                .orElse(false);
    }
}
