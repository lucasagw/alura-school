package br.com.alura.aluraschool.service;


import br.com.alura.aluraschool.constants.AluraSchoolConstants;
import br.com.alura.aluraschool.model.entity.Course;
import br.com.alura.aluraschool.model.entity.CourseFeedback;
import br.com.alura.aluraschool.model.entity.UserSchool;
import br.com.alura.aluraschool.model.form.FeedBackNPS;
import br.com.alura.aluraschool.model.record.CourseFeedbackRequest;
import br.com.alura.aluraschool.model.record.CourseMin;
import br.com.alura.aluraschool.model.record.CourseNPSReport;
import br.com.alura.aluraschool.model.record.UserKeyRequest;
import br.com.alura.aluraschool.repository.CourseFeedbackRepository;
import br.com.alura.aluraschool.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.alura.aluraschool.constants.AluraSchoolConstants.Utils.INITIAL_VALUE;

@Service
public class CourseFeedbackService {

    @Autowired
    private CourseFeedbackRepository feedbackCourseRepository;

    @Autowired
    private UserSchoolService userService;

    @Autowired
    private CourseService courserService;

    @Autowired
    private EnrollmentService enrollmentService;

    public void registerFeedback(JwtAuthenticationToken token, CourseFeedbackRequest courseFeedbackRequest) {

        UserKeyRequest userKey = JwtTokenUtils.getUserKeyFromToken(token);

        UserSchool student = userService.getStudentByUsernameAndEmail(userKey.username(), userKey.email());
        Course course = courserService.findByCode(courseFeedbackRequest.courseCode());

        CourseFeedback courseFeedback = new CourseFeedback(course, student, courseFeedbackRequest.comment(), courseFeedbackRequest.rating());

        courseFeedback = feedbackCourseRepository.saveAndFlush(courseFeedback);

        if (courseFeedback.getId() != null && courseFeedback.getRating() < AluraSchoolConstants.FeedbackRating.DETRACTORS) {

            sendCourseFeedbackEmail(courseFeedbackRequest, course);
        }
    }

    private static void sendCourseFeedbackEmail(CourseFeedbackRequest courseFeedbackRequest, Course course) {
        String body = "The course " + course.getName() + " received a rating of "
                + courseFeedbackRequest.rating() + "\nwith the following comment: " + courseFeedbackRequest.comment();

        EmailSenderService.send(course.getInstructor().getUserKey().getEmail(), AluraSchoolConstants.EmailSend.SUBJECTS, body);
    }

    public CourseNPSReport getNpsReport() {

        List<CourseMin> courses = enrollmentService.listCourseForNPS();

        List<FeedBackNPS> listOfFeedbackNPS = new ArrayList<>();

        for (CourseMin course : courses) {

            int promoter = 0, detractor = 0;

            List<CourseFeedback> feedbacks = feedbackCourseRepository.listFeedbackByCourseId(course.id());

            for (CourseFeedback courseFeedback : feedbacks) {
                if (courseFeedback.getRating() >= 9) {
                    promoter++;
                }
                if (courseFeedback.getRating() <= 6) {
                    detractor++;
                }
            }
            double totalFeedbacks = feedbacks.size();
            double nps = calculateNPS(promoter, detractor, totalFeedbacks);
            int roundedNPS = (int) Math.round(nps);

            listOfFeedbackNPS.add(new FeedBackNPS(course.code(), course.name(), roundedNPS));
        }
        return new CourseNPSReport(listOfFeedbackNPS);
    }

    private double calculateNPS(int promoters, int detractors, double totalFeedbacks) {

        if (totalFeedbacks == 0) {
            return 0.0;
        }
        return ((promoters - detractors) / totalFeedbacks) * 100.0;
    }

}
