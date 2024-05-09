package br.com.alura.aluraschool.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true, length = 10) /*O código de um curso deve ser textual, sem espaços, nem caracteres numéricos e nem
    caracteres especiais, mas pode ser separado por - , exemplo: spring-boot-avancado . */
    private String code; // (máximo de caracteres = 10)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "username", referencedColumnName = "username"),
            @JoinColumn(name = "email", referencedColumnName = "email")
    })
    private User instructor;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "deactivated_at")
    private LocalDate deactivatedAt;
}
