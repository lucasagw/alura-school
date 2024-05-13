package br.com.alura.aluraschool.model.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserForm {

    @NotBlank
    private String name;

    @Pattern(regexp = "^[a-z]+$", message = "Username must consist only of lowercase letters, without numerals or spaces.")
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
