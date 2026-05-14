package com.project.student_manager.entities;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String fullName;

    private String email;

    private String matricNumber;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Enumerated(EnumType.STRING)
    private Department department;

    private LocalDate enrolledAt;

    @OneToMany(mappedBy = "student")
    private List<EnrollmentEntity> enrollments;

    @PrePersist
    public void onCreate() {
        this.enrolledAt = LocalDate.now();
    }
}
