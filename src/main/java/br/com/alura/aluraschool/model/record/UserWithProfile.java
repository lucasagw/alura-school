package br.com.alura.aluraschool.model.record;

import java.util.Set;

public record UserWithProfile(String name, String email, Set<String> profiles) {
}
