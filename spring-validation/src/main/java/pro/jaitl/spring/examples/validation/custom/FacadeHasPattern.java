package pro.jaitl.spring.examples.validation.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy= FacadeHasPatternValidator.class)
@Documented
public @interface FacadeHasPattern {
    String message() default "Facade fields don't have the string pattern";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String stringPattern();
}
