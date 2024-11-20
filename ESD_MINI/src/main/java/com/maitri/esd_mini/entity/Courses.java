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
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

//    @Column(unique = true, nullable = false)
//    private String courseCode;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(columnDefinition = "TEXT")
//    private String description;
//
//    @Column(nullable = false)
//    private int year;
//
//    @Column(nullable = false)
//    private int term;
//
//    private String faculty;
//    private int credits;
//    private int capacity;
}
