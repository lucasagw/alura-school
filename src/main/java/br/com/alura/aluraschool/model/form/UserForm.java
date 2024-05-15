package br.com.alura.aluraschool.model.form;

import br.com.alura.aluraschool.util.SetPattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Set;

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

    @SetPattern(regexp = "^(?i)(ADMIN|INSTRUCTOR|STUDENT)$")
    private Set<String> profiles;

    @NotBlank
    private String password;

}
