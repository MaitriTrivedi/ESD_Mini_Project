package com.maitri.esd_mini.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student_Courses")
public class Student_Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;
}
