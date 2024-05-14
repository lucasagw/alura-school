package br.com.alura.aluraschool.service;

import br.com.alura.aluraschool.model.entity.Course;
import br.com.alura.aluraschool.model.entity.UserSchool;
import br.com.alura.aluraschool.model.form.CourseForm;
import br.com.alura.aluraschool.model.record.CourseItem;
import br.com.alura.aluraschool.model.record.CourseResponse;
import br.com.alura.aluraschool.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courserRepository;

    @Autowired
    private UserSchoolService userService;


    public void register(CourseForm courseForm) {

        var user = findInstructor(courseForm.getInstructorUsername(), courseForm.getInstructorEmail());

        String courseCode = generateUniqueCourseCode(courseForm);

        createAndSaveCourse(courseForm, user, courseCode);
    }

    private UserSchool findInstructor(String username, String email) {

        return userService.findInstructorByUsernameAndEmail(username, email);
    }

    private String generateUniqueCourseCode(CourseForm courseForm) {

        String courseCode;
        boolean courseExists;

        do {
            courseCode = courseForm.generateCourseCode();
            courseExists = courserRepository.existsByCode(courseCode);
        } while (courseExists);

        return courseCode;
    }

    private void createAndSaveCourse(CourseForm courseForm, UserSchool user, String courseCode) {

        var course = new Course(courseForm.getName(), courseForm.getDescription());

        course.setInstructor(user);
        course.setCode(courseCode);
        course.setCreatedAt(LocalDate.now());

        courserRepository.save(course);
    }

    public void disableCourse(String code) {

        Course course = findByCode(code);

        course.setStatus(false);
        course.setDeactivatedAt(LocalDate.now());

        courserRepository.save(course);
    }

    public Course findByCode(String code) {

        return courserRepository.findByCode(code).orElseThrow(() -> new NoSuchElementException("Course not found with code: " + code));
    }

    public CourseResponse listCourses(boolean status, int page, int pageSize) {

        var courses = courserRepository.findByStatus(status, PageRequest.of(page, pageSize, Sort.Direction.ASC, "name"))
                .map(course ->
                        new CourseItem(
                                course.getName(),
                                course.getCode(),
                                course.getInstructor().getUserKey().getUsername(),
                                course.getInstructor().getUserKey().getEmail(),
                                course.getDescription(),
                                course.isStatus(),
                                course.getCreatedAt(),
                                course.getDeactivatedAt()
                        )
                );

        return new CourseResponse(courses.getContent(), page, pageSize, courses.getTotalPages(), courses.getTotalElements());
    }
}

