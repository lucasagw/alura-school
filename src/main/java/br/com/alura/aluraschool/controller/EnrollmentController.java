package br.com.alura.aluraschool.controller;

import br.com.alura.aluraschool.model.record.CreateEnrollmentRequest;
import br.com.alura.aluraschool.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
@Validated
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/create")
    public ResponseEntity<Void> register(@Valid @RequestBody CreateEnrollmentRequest createEnrollmentRequest) {

        enrollmentService.createEnrollment(createEnrollmentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

