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

        validateFeedback(student, course);

        CourseFeedback courseFeedback = new CourseFeedback(course, student, courseFeedbackRequest.comment(), courseFeedbackRequest.rating());

        courseFeedback = feedbackCourseRepository.saveAndFlush(courseFeedback);

        if (courseFeedback.getId() != null && courseFeedback.getRating() < AluraSchoolConstants.FeedbackRating.DETRACTORS) {

            sendCourseFeedbackEmail(courseFeedbackRequest, course);
        }
    }

    private void validateFeedback(UserSchool student, Course course) {

        var feedbackResult = feedbackCourseRepository.existsByStudentAndCourseCode(student, course.getCode());

        if (feedbackResult) {
            throw new IllegalArgumentException("Feedback already registered for this course");
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

            int promoter = INITIAL_VALUE;
            int detractor = INITIAL_VALUE;

            List<CourseFeedback> feedbacks = feedbackCourseRepository.listFeedbackByCourseId(course.id());

            for (CourseFeedback courseFeedback : feedbacks) {
                int rating = courseFeedback.getRating();

                if (rating >= AluraSchoolConstants.FeedbackRating.PROMOTERS) {
                    promoter++;
                } else if (rating <= AluraSchoolConstants.FeedbackRating.DETRACTORS) {
                    detractor++;
                }
            }
            int totalFeedbacks = feedbacks.size();
            int nps = calculateNPS(promoter, detractor, totalFeedbacks);

            listOfFeedbackNPS.add(new FeedBackNPS(course.code(), course.name(), nps));
        }
        return new CourseNPSReport(listOfFeedbackNPS);
    }

    private int calculateNPS(int promoters, int detractors, int totalFeedbacks) {

        if (totalFeedbacks == 0) {
            throw new IllegalArgumentException("No feedbacks for this course");
        }
        double nps = ((double) (promoters - detractors) / totalFeedbacks) * AluraSchoolConstants.Utils.PERCENTAGE;

        return (int) Math.round(nps);
    }

}
