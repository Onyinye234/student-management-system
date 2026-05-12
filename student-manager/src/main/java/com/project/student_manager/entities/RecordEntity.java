package com.project.student_manager.entities;

import com.project.student_manager.enums.Grade;
import com.project.student_manager.enums.Semester;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;

    @OneToOne
    @JoinColumn(name = "enrollmentId", unique = true)
    private EnrollmentEntity enrollment;

    @Column
    private Float ca;

    @Column
    private Float exam;

    @Column
    private Float total;

    @Enumerated(EnumType.STRING)
    private Grade grade;



}
