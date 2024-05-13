package br.com.alura.aluraschool.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "course_feedback")
@Getter
@ToString
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "student_username", referencedColumnName = "username", nullable = false),
            @JoinColumn(name = "student_email", referencedColumnName = "email", nullable = false)
    })
    private UserSchool student;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @Setter
    private LocalDate createdAt;

    public CourseFeedback() {
    }

    public CourseFeedback(Course course, UserSchool student, String comment, int rating) {
        this.course = course;
        this.student = student;
        this.comment = comment;
        this.rating = rating;
    }
}
