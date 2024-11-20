package com.maitri.esd_mini.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Students {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int studentId;

        private String password;

        @Column(unique = true)
        private int rollNumber;

        private String firstName;
        private String lastName;
        private String email;

        private String photographPath;
        private BigDecimal cgpa;
        private int totalCredits;

        private Integer graduationYear;

        @ManyToOne
        @JoinColumn(name = "domain_id")
        private Domain domain;

        @ManyToOne
        @JoinColumn(name = "specialisation_id")
        private Specialisation specialisation;

        @ManyToOne
        @JoinColumn(name = "placement_id")
        private Placements placement;
}
