package com.practice.spring.validate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import com.practice.spring.validate.FriendConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ METHOD, FIELD })
@Constraint(validatedBy = FriendConstraintValidator.class)
public @interface FriendConstraint {
	String message() default "{javax.validation.constraints.NotNull.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}