package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnufMinPartsValidator.class)
public @interface ValidInventoryInRangeMin {
    // More specific error messages are defined on use of this annotation
    String message() default "Part inventory count below allowed range!";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

