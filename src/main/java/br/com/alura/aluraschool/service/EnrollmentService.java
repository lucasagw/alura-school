package br.com.alura.aluraschool.service;


import br.com.alura.aluraschool.constants.AluraSchoolConstants;
import br.com.alura.aluraschool.model.entity.Course;
import br.com.alura.aluraschool.model.entity.Enrollment;
import br.com.alura.aluraschool.model.entity.UserSchool;
import br.com.alura.aluraschool.model.record.CourseMin;
import br.com.alura.aluraschool.model.record.CreateEnrollmentRequest;
import br.com.alura.aluraschool.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserSchoolService userService;

    @Autowired
    private CourseService courserService;

    public void createEnrollment(CreateEnrollmentRequest createEnrollmentRequest) {

        UserSchool userSchool = userService.findStudentByUsernameAndEmail(createEnrollmentRequest.student().username(),
                createEnrollmentRequest.student().email());

        Course course = courserService.findByCode(createEnrollmentRequest.courseCode());

        boolean userEnrolled = enrollmentRepository.isUserEnrolled(userSchool, course);

        if (course.isStatus())
            if (!userEnrolled) {

                Enrollment enrollment = new Enrollment();
                enrollment.setUser(userSchool);
                enrollment.setCourse(course);
                enrollment.setCreatedAt(LocalDate.now());

                enrollmentRepository.save(enrollment);
            } else {
                throw new IllegalArgumentException("User already enrolled in the course");
            }
        else {
            throw new IllegalArgumentException("Course is not available");
        }
    }

    public List<CourseMin> listCourseForNPS() {

        return enrollmentRepository.listCourseForNPS(AluraSchoolConstants.FeedbackRating.NUMBER_OF_ENROLLMENTS);
    }

}
