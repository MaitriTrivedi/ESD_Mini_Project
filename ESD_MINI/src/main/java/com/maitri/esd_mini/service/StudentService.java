package com.maitri.esd_mini.service;

import com.maitri.esd_mini.dto.EnrollStudentRequest;
import com.maitri.esd_mini.dto.ShowCoursesRequest;
import com.maitri.esd_mini.dto.StudentRequest;
import com.maitri.esd_mini.entity.*;
import com.maitri.esd_mini.helper.EncryptionService;
import com.maitri.esd_mini.mapper.StudentMapper;
import com.maitri.esd_mini.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    // To store entity into sql database
    private final StudentRepo repo;
    private final DomainRepo domainRepository;
    private final SpecialisationRepo specialisationRepository;
    private final PlacementRepo placementRepository;
    private final CoursesRepo coursesRepo;
    private final StudentRepo studentsRepo;

    // To convert dto to entity
    private final StudentMapper map;
    private final StudentMapper createMapper;
//    private final CustomerDeleteMapper deleteMapper;

    private final EncryptionService encryptionService;
    private final Student_CoursesRepository studentCourseRepository;
    private final CoursePrerequisiteRepository coursePrerequisiteRepository;
    private final Student_Courses studentCourse;
    private final Students student;

   /* @Autowired
    public EnrollmentService(Student_CoursesRepository studentCourseRepository,
                             CoursePrerequisiteRepository coursePrerequisiteRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.coursePrerequisiteRepository = coursePrerequisiteRepository;
    }*/

    public List<Map<String, Object>> showAllowedCourses(String student_id) {
        System.out.println("==================== create service");
        int studentId = Integer.parseInt(student_id);

        List<Courses> all_courses = coursesRepo.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Courses course : all_courses) {
            if(!studentCourseRepository.existsByStudentAndCourse(studentId, course.getCourse_id())
            && (coursePrerequisiteRepository.findUnmetPrerequisites(studentId, course.getCourse_id()).isEmpty())){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("courseId", course.getCourse_id());
                courseMap.put("courseName", course.getCourseCode());
                courseMap.put("credits", course.getCredits());
                courseMap.put("description", course.getDescription());
                result.add(courseMap);
            }
        }
        return result;
    }

    public String createStudent(StudentRequest request) {
        System.out.println("==================== create service");
        try{
        // This will convert our dto to entity using Mapper
        Students student = createMapper.toEntity(request);
        System.out.println("==================== 2create service");
        student.setPassword(encryptionService.encode(student.getPassword()));
        System.out.println("==================== 3create service");
        System.out.println(student);
        System.out.println(request);
        Domain domain = new Domain();
        domainRepository.save(domain); // Save new Domain

        Specialisation specialisation = new Specialisation();
        specialisationRepository.save(specialisation); // Save new Specialisation

        Placements placement = new Placements();
        placementRepository.save(placement); // Save new Placement

//        Students student = new Students();
        student.setDomain(domain);

        student.setSpecialisation(specialisation);
        student.setPlacement(placement);
        // Stores entity into database using Repo
        repo.save(student);
        System.out.println("==================== 4create service");
        return "Account Created Successfully";
        }
        catch(Exception e){
            return "Account Creation Failed due to : " + e.getMessage();
        }
    }

    public String enrollStudent(EnrollStudentRequest req){//long studentId, long courseId) {
        // Check if prerequisites are satisfied
        int studentId = req.studentId();
        int courseId = req.courseId();
        List<Integer> unmetPrerequisites = coursePrerequisiteRepository.findUnmetPrerequisites(studentId, courseId);
        if (!unmetPrerequisites.isEmpty()) {
            return "Prerequisites not satisfied for course ID: " + courseId;
        }

        // Check if the student is already enrolled
        if (studentCourseRepository.existsByStudentAndCourse(studentId, courseId)) {
            return "Student is already enrolled in course ID: " + courseId;
        }
        Students st = studentsRepo.findById(studentId).orElse(null);
        Courses cs = coursesRepo.findById(courseId).orElse(null);
        // Enroll the student
        // Student_Courses studentCourse = new StudentCourse();
        studentCourse.setStudent(st); // Assuming constructor or ID setter
        studentCourse.setCourse(cs); // Assuming constructor or ID setter
        studentCourseRepository.save(studentCourse);

        return "Student successfully enrolled in course ID: " + courseId;
    }
}
