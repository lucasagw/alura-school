package br.com.alura.aluraschool.model.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.security.SecureRandom;

@Getter
public class CourseForm {

    @NotBlank
    private String name;

    @Pattern(regexp = "^[a-z]+$", message = "Username must consist only of lowercase letters, without numerals or spaces.")
    @NotBlank
    private String instructorUsername;

    @Email(message = "The format of the given email is incorrect!")
    @NotBlank
    private String instructorEmail;

    @NotBlank
    private String description;

    /**
     * This method generates a course code.
     *
     * @return the generated course code.
     * @Author: lucasagw
     */
    public String generateCourseCode() {

        final String ALPHA_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int UID_LENGTH = 10;

        SecureRandom random = new SecureRandom();
        StringBuilder uidBuilder = new StringBuilder();

        for (int i = 0; i < UID_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHA_CHARS.length());
            char randomChar = ALPHA_CHARS.charAt(randomIndex);
            uidBuilder.append(randomChar);
        }
        return uidBuilder.toString();
    }
}


