package pro.jaitl.spring.examples.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pro.jaitl.spring.examples.validation.dto.FacadeDto;

public class FacadeHasPatternValidator implements ConstraintValidator<FacadeHasPattern, FacadeDto> {
    private String stringPattern;

    @Override
    public void initialize(FacadeHasPattern constraintAnnotation) {
        stringPattern = constraintAnnotation.stringPattern();
    }

    @Override
    public boolean isValid(FacadeDto value, ConstraintValidatorContext context) {
        if (value == null || value.getDescription() == null || value.getName() == null) {
            return false;
        }
        if (value.getDescription().contains(stringPattern) && value.getName().contains(stringPattern)) {
            return true;
        }
        return false;
    }
}
