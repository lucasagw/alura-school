package br.com.alura.aluraschool.model.record;

import java.time.LocalDate;

public record CourseItem(String name, String code, String instructorUsername, String instructorEmail,
                         String description, boolean status,
                         LocalDate createdAt, LocalDate deactivatedAt) {
}
