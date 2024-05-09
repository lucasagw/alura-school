package br.com.alura.aluraschool.model.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import br.com.alura.aluraschool.model.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_school")
@Data
public class User {

    @EmbeddedId
    @Column(name = "user_key")
    private UserKey userKey;

    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private RoleEnum role;

    @Column(name = "creation_date")
    @CreatedDate
    private Date creationDate;


}