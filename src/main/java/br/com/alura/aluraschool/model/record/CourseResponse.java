package br.com.alura.aluraschool.model.record;

import java.util.List;

public record CourseResponse(List<CourseItem> courseItems,
                             int page,
                             int pageSize,
                             int totalPages,
                             long totalElements) {
}
