package pro.jaitl.spring.examples.validation.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Pattern(regexp = "\\+7\\(\\d{3}\\)\\d{3}\\-\\d{2}\\-\\d{2}")
@Size(min = 16, max = 16)
@Constraint(validatedBy = { })
@ReportAsSingleViolation
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface RusPhoneNumber {
    String message() default "Wrong phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
