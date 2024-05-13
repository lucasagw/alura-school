package br.com.alura.aluraschool.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserEnrollment(@Pattern(regexp = "^[a-z]+$", message = "Username must consist only of lowercase letters, without numerals or spaces.")
                             @NotBlank String username,
                             @NotBlank String email) {
}
