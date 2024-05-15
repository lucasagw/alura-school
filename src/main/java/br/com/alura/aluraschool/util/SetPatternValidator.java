package br.com.alura.aluraschool.util;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class SetPatternValidator implements ConstraintValidator<SetPattern, Set<String>> {

    private String regexp;

    @Override
    public void initialize(SetPattern constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(Set<String> profiles, ConstraintValidatorContext context) {
        if (profiles == null || profiles.isEmpty()) {
            return false;
        }

        for (String profile : profiles) {
            if (!profile.matches(regexp)) {
                return false;
            }
        }
        return true;
    }
}
