package br.com.alura.aluraschool.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
@Getter
@ToString
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "student_name", referencedColumnName = "username", nullable = false),
            @JoinColumn(name = "student_email", referencedColumnName = "email", nullable = false)
    })
    @Setter
    private UserSchool user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    @Setter
    private Course course;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @Setter
    private LocalDate createdAt;
}
