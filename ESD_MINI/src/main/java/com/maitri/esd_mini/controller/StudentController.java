package com.maitri.esd_mini.controller;

import com.maitri.esd_mini.dto.StudentRequest;
import com.maitri.esd_mini.entity.Courses;
import com.maitri.esd_mini.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.maitri.esd_mini.service.StudentService;
import com.maitri.esd_mini.entity.Students;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    // final : it will create only one object in whole project container, when we run the project
    private final StudentService studentService;

    @PostMapping("/show_courses")
    public ResponseEntity<Courses> showCourses(@RequestBody @Valid StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.showAllowedCourses(studentRequest));
    }

    @PostMapping("/create_account") // for post request
    public ResponseEntity<Students> createCustomer(@RequestBody @Valid StudentRequest studentRequest) {
        System.out.println("==================== create controller");
        return ResponseEntity.ok(studentService.createStudent(studentRequest));
    }

}