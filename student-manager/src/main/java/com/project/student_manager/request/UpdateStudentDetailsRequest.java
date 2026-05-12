package com.project.student_manager.request;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateStudentDetailsRequest {
    private String fullName;
    @Email(message="Invalid email")
    private String email;
    private Level level;
    private Department department;



}
