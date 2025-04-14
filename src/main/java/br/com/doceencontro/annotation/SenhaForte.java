package br.com.doceencontro.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = SenhaForteValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaForte {
	String message() default "A senha deve ter no mínimo 8 caracteres, incluindo uma maiúscula, uma minúscula, um número e um caractere especial.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}