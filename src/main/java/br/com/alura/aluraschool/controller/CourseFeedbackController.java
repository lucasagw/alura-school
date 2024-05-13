package br.com.alura.aluraschool.controller;


import br.com.alura.aluraschool.model.record.CourseFeedbackRequest;
import br.com.alura.aluraschool.model.record.CourseNPSReport;
import br.com.alura.aluraschool.service.CourseFeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-feedback")
@Validated
public class CourseFeedbackController {

    @Autowired
    private CourseFeedbackService courseFeedbackService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_STUDENT')")
    public ResponseEntity<Void> register(JwtAuthenticationToken token, @Valid @RequestBody CourseFeedbackRequest courseFeedbackRequest) {

        courseFeedbackService.registerFeedback(token, courseFeedbackRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/generate-report")
    public ResponseEntity<CourseNPSReport> generateReport() {

        return ResponseEntity.ok(courseFeedbackService.getNpsReport());
    }
}
