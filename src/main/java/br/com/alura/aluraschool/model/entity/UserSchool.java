package br.com.alura.aluraschool.model.entity;

import br.com.alura.aluraschool.model.record.LoginRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user_school")
@Getter
@ToString
public class UserSchool {

    @EmbeddedId
    @Column(name = "user_key")
    private UserKey userKey;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_profile",
            joinColumns = {
                    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false),
                    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
            },
            inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    )
    @Setter
    private Set<Profile> profiles;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @Setter
    private LocalDate createdAt;

    public UserSchool() {
    }

    public UserSchool(UserKey userKey, String name, String password) {
        this.userKey = userKey;
        this.name = name;
        this.password = password;
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {

        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}