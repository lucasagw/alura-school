package br.com.alura.aluraschool.controller;

import br.com.alura.aluraschool.model.form.UserForm;
import br.com.alura.aluraschool.model.record.UserWithProfile;
import br.com.alura.aluraschool.service.UserSchoolService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Validated
public class UserSchoolController {

    @Autowired
    private UserSchoolService userService;

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<UserWithProfile> getUser(@Pattern(regexp = "^[a-z]+$", message = "Username must consist only of lowercase letters, without numerals or spaces.")
                                                   @PathVariable String username) {

        var user = userService.findByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(new UserWithProfile(user.getName(), user.getUserKey().getEmail(), user.getProfiles()));
    }

    @PostMapping("/create-account")
    public ResponseEntity<Void> register(@Valid @RequestBody UserForm userForm) {

        userService.register(userForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}