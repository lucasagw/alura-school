package br.com.alura.aluraschool.repository;

import br.com.alura.aluraschool.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {


    boolean existsByCode(String courseCode);


    Optional<Course> findByCode(String code);


    Page<Course> findByStatus(boolean status, Pageable pageable);

}
