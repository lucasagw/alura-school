package br.com.alura.aluraschool.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DisableCourse(@Pattern(regexp = "^[a-zA-Z]+$", message = "Course code must consist only of letters without numerals, spaces, or special characters.")
                            @NotBlank String code) {
}
