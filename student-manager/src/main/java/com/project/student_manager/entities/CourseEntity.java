package com.project.student_manager.entities;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import com.project.student_manager.enums.Semester;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseCode;
    private String courseName;

    private String courseUnit;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToMany(mappedBy = "course")
    private List<EnrollmentEntity> enrollments;
}
