package com.project.student_manager.entities;
<<<<<<< HEAD
import com.project.student_manager.enums.Department;
=======

>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
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
    private Department department;

    @Enumerated(EnumType.STRING)
    private Level level;

    private LocalDate enrolledAt;

    @OneToMany(mappedBy = "student")
    private List<EnrollmentEntity> enrollments;

    @PrePersist
    public void onCreate() {
        this.enrolledAt = LocalDate.now();
    }

<<<<<<< HEAD
    public StudentEntity(String fullName, String email, String matricNumber, Level level, Department department) {
=======
    public StudentEntity(
        String fullName,
        String email,
        String matricNumber,
        Level level
    ) {
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
        this.fullName = fullName;
        this.email = email;
        this.matricNumber = matricNumber;
        this.level = level;
        this.department = department;
    }
}
