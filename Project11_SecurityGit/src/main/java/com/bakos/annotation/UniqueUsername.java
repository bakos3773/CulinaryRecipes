package com.bakos.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.data.mongodb.core.mapping.Document;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueUsernameValidator.class})
public @interface UniqueUsername {
	String message() default "This username exists! :)";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
