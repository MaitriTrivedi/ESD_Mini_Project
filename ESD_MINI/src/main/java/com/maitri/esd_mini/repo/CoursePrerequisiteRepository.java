package com.maitri.esd_mini.repo;

import com.maitri.esd_mini.entity.CoursePrerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePrerequisiteRepository extends JpaRepository<CoursePrerequisite, Integer> {
    @Query("""
        SELECT cp.coursePrerequisiteId
        FROM CoursePrerequisite cp
        WHERE cp.course.course_id = :courseId AND cp.coursePrerequisiteId NOT IN (
            SELECT sc.course.course_id
            FROM Student_Courses sc
            WHERE sc.student.studentId = :studentId
        )
    """)
    List<Integer> findUnmetPrerequisites(int studentId, int courseId);
}
