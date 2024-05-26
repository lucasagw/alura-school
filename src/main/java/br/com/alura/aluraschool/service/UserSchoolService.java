package br.com.alura.aluraschool.service;

import br.com.alura.aluraschool.model.entity.Profile;
import br.com.alura.aluraschool.model.entity.UserKey;
import br.com.alura.aluraschool.model.entity.UserSchool;
import br.com.alura.aluraschool.model.form.UserForm;
import br.com.alura.aluraschool.model.record.UserWithProfile;
import br.com.alura.aluraschool.repository.ProfileRepository;
import br.com.alura.aluraschool.repository.UserSchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserSchoolService {

    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Autowired
    private UserSchoolRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;


    public UserWithProfile findByUsername(String username) {

        var user = userRepository.findByUserKeyUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));

        Set<String> profiles = user.getProfiles().stream().map(Profile::getName).collect(Collectors.toSet());

        return new UserWithProfile(user.getName(), user.getUserKey().getEmail(), profiles);
    }

    public UserSchool findByUsernameAndEmail(String username, String email) {

        return userRepository.findByUsernameAndEmail(username, email)
                .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username + " and email: " + email));
    }

    public UserSchool findInstructorByUsernameAndEmail(String username, String email) {

        UserSchool user = findByUsernameAndEmail(username, email);

        user.getProfiles().stream()
                .filter(profile -> profile.getName().equals(Profile.Values.INSTRUCTOR.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("User with username and email '" + username + "' '" + email + "' is not an instructor"));

        return user;
    }

    public UserSchool findStudentByUsernameAndEmail(String username, String email) {

        UserSchool user = findByUsernameAndEmail(username, email);

        user.getProfiles().stream()
                .filter(profile -> profile.getName().equals(Profile.Values.STUDENT.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("User with username and email '" + username + "' '" + email + "' is not an student"));

        return user;
    }

    public UserSchool getStudentByUsernameAndEmail(String username, String email) {

        return userRepository.getStudentByUsernameAndEmail(username, email);
    }

    public void register(UserForm userForm) {

        verifyUserNotDuplicate(userForm);

        Set<String> collectNames = convertProfiles(userForm.getProfiles());

        var profiles = profileRepository.findByNames(collectNames);

        var userKey = new UserKey(userForm.getUsername(), userForm.getEmail());

        var user = new UserSchool(userKey, userForm.getName(), bCrypt.encode(userForm.getPassword()));

        user.setProfiles(profiles);

        user.setCreatedAt(LocalDate.now());

        userRepository.save(user);
    }

    private void verifyUserNotDuplicate(UserForm userForm) {

        boolean userExists = userRepository.existsByUsernameOrEmail(userForm.getUsername(), userForm.getEmail());

        if (userExists) {
            throw new IllegalArgumentException("A user with the same email or username already exists.");
        }
    }

    private static Set<String> convertProfiles(Set<String> profiles) {

        return profiles.stream().map(String::toUpperCase).collect(Collectors.toSet());
    }

}