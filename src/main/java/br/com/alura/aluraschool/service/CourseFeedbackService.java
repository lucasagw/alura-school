package br.com.alura.aluraschool.service;

import br.com.alura.aluraschool.constants.AluraSchoolConstants;
import br.com.alura.aluraschool.model.entity.Course;
import br.com.alura.aluraschool.model.entity.CourseFeedback;
import br.com.alura.aluraschool.model.entity.UserSchool;
import br.com.alura.aluraschool.model.form.FeedBackNPS;
import br.com.alura.aluraschool.model.record.CourseFeedbackRequest;
import br.com.alura.aluraschool.model.record.CourseNPSReport;
import br.com.alura.aluraschool.model.record.UserKeyRequest;
import br.com.alura.aluraschool.repository.CourseFeedbackRepository;
import br.com.alura.aluraschool.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseFeedbackService {

    @Autowired
    private CourseFeedbackRepository feedbackCourseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courserService;

    @Autowired
    private EnrollmentService enrollmentService;

    public void registerFeedback(JwtAuthenticationToken token, CourseFeedbackRequest courseFeedbackRequest) {

        UserKeyRequest userKey = JwtTokenUtils.getUserKeyFromToken(token);

        UserSchool student = userService.findStudentByUsernameAndEmail(userKey.username(), userKey.email());
        Course course = courserService.findByCode(courseFeedbackRequest.courseCode());

        CourseFeedback courseFeedback = new CourseFeedback(course, student, courseFeedbackRequest.comment(), courseFeedbackRequest.rating());

        courseFeedback = feedbackCourseRepository.saveAndFlush(courseFeedback);

        if (courseFeedback.getId() != null && courseFeedback.getId() > 0 && courseFeedback.getRating() < 6) {
            String body = "The course " + course.getName() + " received a rating of " + courseFeedbackRequest.rating() + "\nwith the following comment: " + courseFeedbackRequest.comment();

            EmailSenderService.send(course.getInstructor().getUserKey().getEmail(), AluraSchoolConstants.EmailSend.subjects, body);
        }
    }

    public CourseNPSReport getNpsReport() {

        List<String> codeCourses = enrollmentService.listCourseForNPS();
        List<FeedBackNPS> listOfFeedbackNPS = new ArrayList<>();

        for (String course : codeCourses) {

            int promoter = 0, detractor = 0;

            List<CourseFeedback> feedbacks = feedbackCourseRepository.listFeedbackByCode(course);

            for (CourseFeedback courseFeedback : feedbacks) {
                if (courseFeedback.getRating() >= 9) {
                    promoter++;
                }
                if (courseFeedback.getRating() <= 6) {
                    detractor++;
                }
                int nps = ((promoter - detractor) / feedbacks.size()) * 100;

                listOfFeedbackNPS.add(new FeedBackNPS(course, nps));
            }
        }
        return new CourseNPSReport(listOfFeedbackNPS);
    }

}
