package br.com.alura.aluraschool.controller;

import br.com.alura.aluraschool.model.form.CourseForm;
import br.com.alura.aluraschool.model.record.CourseResponse;
import br.com.alura.aluraschool.model.record.DisableCourse;
import br.com.alura.aluraschool.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@Validated
public class CourseController {

    @Autowired
    private CourseService courserService;


    @PostMapping( "/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> register(@Valid @RequestBody CourseForm courseForm) {

        courserService.register(courseForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/disable")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> disableCourse(@Valid @RequestBody DisableCourse code) {

        courserService.disableCourse(code.code());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<CourseResponse> listCourses(@RequestParam(defaultValue = "true") boolean status,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(courserService.listCourses(status, page, pageSize));
    }

}
