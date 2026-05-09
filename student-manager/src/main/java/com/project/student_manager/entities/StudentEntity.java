package com.project.student_manager.entities;
import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    private Department department;

    @Enumerated(EnumType.STRING)
    private Level level;

    private LocalDate enrolledAt;

    @OneToMany(mappedBy = "student")
    private List <EnrollmentEntity> enrollments;

    @PrePersist
    public void onCreate(){
        this.enrolledAt = LocalDate.now();



    }

    public StudentEntity(String fullName, String email, String matricNumber, Level level, Department department) {
        this.fullName = fullName;
        this.email = email;
        this.matricNumber = matricNumber;
        this.level = level;
        this.department = department;
    }

}
