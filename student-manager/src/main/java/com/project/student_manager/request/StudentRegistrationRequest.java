package com.project.student_manager.request;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRegistrationRequest {

    @NotBlank(message = "Full name field cannot be blank")
    private String fullName;

    @NotNull(message = "Email field cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Matric Number field cannot be empty")
    @Pattern(regexp = "^\\d{2}/\\d{4}$", message = "Invalid pattern")
    private String matricNumber;

    @NotNull(message = "Department field cannot be null")
    private Department department;

    @NotNull(message = "Level field cannot be null")
    private Level level;
}
