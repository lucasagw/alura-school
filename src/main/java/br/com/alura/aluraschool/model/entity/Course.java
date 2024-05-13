package br.com.alura.aluraschool.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@Getter
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false, length = 10)
    @Setter
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "instructor_username", referencedColumnName = "username", nullable = false),
            @JoinColumn(name = "instructor_email", referencedColumnName = "email", nullable = false)
    })
    @Setter
    private UserSchool instructor;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Setter
    private boolean status;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @Setter
    private LocalDate createdAt;

    @Column(name = "deactivated_at")
    @Setter
    private LocalDate deactivatedAt;

    public Course() {
    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = true;
    }
}
