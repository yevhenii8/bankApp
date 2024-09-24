package org.domahaiev.bankapp.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.domahaiev.bankapp.validation.constraint.UUIDAnnotationChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UUIDAnnotationChecker.class})
public @interface UUIDChecker {
    String message() default "Invalid UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
