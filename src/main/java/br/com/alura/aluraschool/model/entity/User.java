package br.com.alura.aluraschool.model.entity;

import br.com.alura.aluraschool.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "user_school")
@Data
public class User {

    @EmbeddedId
    @Column(name = "user_key")
    private UserKey userKey;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdAt;
}