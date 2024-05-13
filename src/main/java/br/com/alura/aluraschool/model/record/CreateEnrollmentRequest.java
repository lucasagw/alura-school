package br.com.alura.aluraschool.model.record;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateEnrollmentRequest(@Pattern(regexp = "^[a-zA-Z]+$", message = "Course code must consist only of letters without numerals, spaces, or special characters.")
                                      @NotBlank String courseCode,
                                      @Valid UserEnrollment student){


}
