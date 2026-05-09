package com.project.student_manager.request;

import com.project.student_manager.enums.Level;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateStudentDetailsRequest {
    private String fullName;
    @Email(message="Invalid email")
    private String email;
    private Level level;



}
