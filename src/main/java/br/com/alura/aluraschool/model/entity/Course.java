package br.com.alura.aluraschool.model.entity;

import java.util.Date;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Column(name = "name")
    private String name;
    
    @Id
    @Column(name = "code") /*O código de um curso deve ser textual, sem espaços, nem caracteres numéricos e nem
    caracteres especiais, mas pode ser separado por - , exemplo: spring-boot-avancado . */
    @Size(max = 10)
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
    
    @Column(name = "creation_date")
    @CreatedDate
    private Date creationDate;
    
    @Column(name = "end_date")
    private Date endDate;
    

}
