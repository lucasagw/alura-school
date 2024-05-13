package br.com.alura.aluraschool.model.form;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserForm {

    @NotBlank
    @Size(min = 8, message = "Name must have at least 8 characters.")
    private String name;

    @Pattern(regexp = "^[a-z]+$", message = "Username must consist only of lowercase letters, without numerals or spaces.")
    @Size(min = 4, message = "Username must have at least 4 characters.")
    @NotBlank
    private String username;

    @Email(message = "The format of the given email is incorrect!")
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?i)(ADMIN|INSTRUCTOR|STUDENT)$", message = "Invalid role. Allowed roles are ADMIN, INSTRUCTOR, STUDENT.")
    @NotBlank
    private String role;

    @NotBlank
    private String password;

}
