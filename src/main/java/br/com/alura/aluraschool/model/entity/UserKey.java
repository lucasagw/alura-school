package br.com.alura.aluraschool.model.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Embeddable
@Data
public class UserKey implements Serializable {
    
    @NotBlank
    @Pattern(regexp = "^[a-z]+$", message = "O username deve conter apenas caracteres minúsculos e sem espaços")
    private String username;

    @Email(message = "The format of the given email is incorrect!")
    @NotBlank
    private String email;

   
}