package br.com.alura.aluraschool.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record CourseFeedbackRequest(@Pattern(regexp = "^[a-zA-Z]+$", message = "Course code must consist only of letters without numerals, spaces, or special characters.")
                                    @NotBlank String courseCode,
                                    @NotBlank String comment,
                                    @PositiveOrZero int rating) {
}
