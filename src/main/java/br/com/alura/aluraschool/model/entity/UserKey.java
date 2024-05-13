package br.com.alura.aluraschool.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@Getter
@ToString
public class UserKey implements Serializable {

    @Column(name = "username", unique = true, length = 20)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    public UserKey() {
    }

    public UserKey(String username, String email) {
        this.username = username;
        this.email = email;
    }
}