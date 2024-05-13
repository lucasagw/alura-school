package br.com.alura.aluraschool.config;

import br.com.alura.aluraschool.model.entity.Profile;
import br.com.alura.aluraschool.model.entity.UserKey;
import br.com.alura.aluraschool.model.entity.UserSchool;
import br.com.alura.aluraschool.repository.ProfileRepository;
import br.com.alura.aluraschool.repository.UserSchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final ProfileRepository profileRepository;

    private final UserSchoolRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(ProfileRepository profileRepository, UserSchoolRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {

        var profileAdmin = profileRepository.findByName(Profile.Values.ADMIN.name());

        var userAdmin = userRepository.findByUserKeyUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin user already exists");
                },
                () -> {
                    var user = new UserSchool(new UserKey("admin", "admin@alura"), "Admin", passwordEncoder.encode("123"));
                    user.setProfiles(Set.of(profileAdmin));
                    user.setCreatedAt(LocalDate.now());

                    userRepository.save(user);
                }
        );
    }
}
