package br.com.alura.aluraschool.repository;

import br.com.alura.aluraschool.model.entity.Course;
import br.com.alura.aluraschool.model.entity.Enrollment;
import br.com.alura.aluraschool.model.entity.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {


    @Query("SELECT COUNT(enrollment) > 0 " +
            "FROM Enrollment enrollment " +
            "WHERE enrollment.user = :userSchool " +
            "AND enrollment.course = :course")
    boolean isUserEnrolled(UserSchool userSchool, Course course);


    @Query(value = "SELECT e.course.id FROM Enrollment e " +
            "GROUP BY e.course.code " +
            "HAVING COUNT(e.course.code) >= 4", nativeQuery = true)
    List<String> listCourseForNPS();
}