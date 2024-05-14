package br.com.alura.aluraschool.repository;

import br.com.alura.aluraschool.model.entity.Course;
import br.com.alura.aluraschool.model.entity.Enrollment;
import br.com.alura.aluraschool.model.entity.UserSchool;
import br.com.alura.aluraschool.model.record.CourseMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {


    @Query("SELECT COUNT(enrollment) > 0 " +
            "FROM Enrollment enrollment " +
            "WHERE enrollment.user = :userSchool " +
            "AND enrollment.course = :course")
    boolean isUserEnrolled(UserSchool userSchool, Course course);


    @Query("SELECT new br.com.alura.aluraschool.model.record.CourseMin(e.course.id, e.course.code, e.course.name) " +
            "FROM Enrollment e " +
            "GROUP BY e.course.id " +
            "HAVING COUNT(e.course.id) >= :numberOfEnrollments ")
    List<CourseMin> listCourseForNPS(int numberOfEnrollments);

}