package com.maitri.esd_mini.controller;

import com.maitri.esd_mini.dto.EnrollStudentRequest;
import com.maitri.esd_mini.dto.ShowCoursesRequest;
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
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    // final : it will create only one object in whole project container, when we run the project
    private final StudentService studentService;

    @GetMapping("/show_courses")
    public ResponseEntity<List<Map<String, Object>> > Student(@RequestBody @Valid ShowCoursesRequest studentRequest) {
        return ResponseEntity.ok(studentService.showAllowedCourses(studentRequest));
    }

    @PostMapping("/create_account")
    public ResponseEntity<String> createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        System.out.println("==================== create controller");
        return ResponseEntity.ok(studentService.createStudent(studentRequest));
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestBody @Valid EnrollStudentRequest enrollStudentRequest) {
        return ResponseEntity.ok(studentService.enrollStudent(enrollStudentRequest));
    }

}