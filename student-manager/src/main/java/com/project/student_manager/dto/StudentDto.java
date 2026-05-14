package com.project.student_manager.dto;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long studentId;
    private String fullName;
    private String email;
    private String matricNumber;
    private Level level;
    private LocalDate enrolledAt;
    private Department department;
}
