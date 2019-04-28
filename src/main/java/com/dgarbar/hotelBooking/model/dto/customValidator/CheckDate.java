package com.dgarbar.hotelBooking.model.dto.customValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDate {
	String message() default "Date is not valid";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
