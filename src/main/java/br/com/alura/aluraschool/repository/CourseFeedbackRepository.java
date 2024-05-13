package br.com.alura.aluraschool.repository;

import br.com.alura.aluraschool.model.entity.CourseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long> {


    @Query("SELECT cf FROM CourseFeedback cf " +
            "WHERE cf.course.code IN :courseCodes")
    List<CourseFeedback> listFeedbackByCode(String courseCodes);
}
