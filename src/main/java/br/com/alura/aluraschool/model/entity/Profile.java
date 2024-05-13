package br.com.alura.aluraschool.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "profile")
@ToString
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Getter
    private String name;


    public enum Values {

        ADMIN(1L),
        INSTRUCTOR(2L),
        STUDENT(3L);

        long profileId;

        Values(long profileId) {
            this.profileId = profileId;
        }

    }
}
