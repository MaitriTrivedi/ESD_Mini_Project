package com.maitri.esd_mini.repo;

import com.maitri.esd_mini.entity.Student_Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Student_CoursesRepository extends JpaRepository<Student_Courses, Integer> {
    @Query("SELECT CASE WHEN COUNT(sc) > 0 THEN true ELSE false END " +
            "FROM Student_Courses sc " +
            "WHERE sc.student.studentId = :studentId " +
            "AND sc.course.course_id = :courseId")
    boolean existsByStudentAndCourse(@Param("studentId") long studentId, @Param("courseId") long courseId);
}
