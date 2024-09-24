package org.domahaiev.bankapp.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.domahaiev.bankapp.validation.constraint.UsernameAnnotationChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameAnnotationChecker.class)
public @interface UsernameChecker {

    String message() default "Invalid username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
