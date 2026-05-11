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

<<<<<<< HEAD
    @NotNull(message="Email field cannot be empty")
    @Email(message="Invalid email")
=======
    @NotNull(message = "Email field cannot be empty")
    @Email(message = "Invalid email")
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
    private String email;

    @NotNull(message = "Matric Number field cannot be empty")
    @Pattern(regexp = "^\\d{2}/\\d{4}$", message = "Invalid pattern")
    private String matricNumber;

    @NotNull(message = "Level field cannot be null")
    private Level level;
<<<<<<< HEAD

    @NotNull(message = "Department field cannot be null")
    private Department department;


=======
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
}
