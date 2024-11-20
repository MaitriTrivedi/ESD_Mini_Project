package com.maitri.esd_mini.service;

import com.maitri.esd_mini.dto.StudentRequest;
import com.maitri.esd_mini.entity.Courses;
import com.maitri.esd_mini.entity.Students;
import com.maitri.esd_mini.helper.EncryptionService;
import com.maitri.esd_mini.mapper.StudentMapper;
import com.maitri.esd_mini.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    // To store entity into sql database
    private final StudentRepo repo;

    // To convert dto to entity
    private final StudentMapper map;
    private final StudentMapper createMapper;
//    private final CustomerDeleteMapper deleteMapper;

    private final EncryptionService encryptionService;

    public Courses showAllowedCourses(StudentRequest request) {
        System.out.println("==================== create service");
        Courses courses = new Courses();
        return courses;
    }

    public Students createStudent(StudentRequest request) {
        System.out.println("==================== create service");

        // This will convert our dto to entity using Mapper
        Students student = createMapper.toEntity(request);
        student.setPassword(encryptionService.encode(student.getPassword()));
        // Stores entity into database using Repo
        repo.save(student);
        return student;
    }
}
