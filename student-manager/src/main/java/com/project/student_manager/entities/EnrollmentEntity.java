package com.project.student_manager.entities;

import com.project.student_manager.enums.Level;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "enrollment")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private CourseEntity course;

    @Column(nullable = false)
    private LocalDate enrolledAt;

    @Column(nullable = false)
    private Level level;

    @PrePersist
    public void onCreate() {
        this.enrolledAt = LocalDate.now();
    }
}
