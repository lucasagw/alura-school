package br.com.alura.aluraschool.model.record;

import br.com.alura.aluraschool.model.entity.Profile;

import java.util.Set;

public record UserWithProfile(String name, String email, Set<Profile> profiles) {
}
