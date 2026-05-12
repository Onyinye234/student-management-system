package com.project.student_manager.entities;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import com.project.student_manager.enums.Semester;
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
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(nullable = false)
    private String academicYear;



    @PrePersist
    public void onCreate() {
        this.enrolledAt = LocalDate.now();
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        if(month >= 9){
            this.academicYear = year +"/" +(year+1);
        }else{
            this.academicYear = (year-1) +"/"+ year;
        }
    }
}
