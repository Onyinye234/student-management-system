package com.project.student_manager.request;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import com.project.student_manager.enums.Semester;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseRegisterationRequest {

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotNull(message = "Course unit is required")
    private String courseUnit;

    @NotNull(message = "Semester is required")
    private Semester semester;

    @NotNull(message = "Level is required")
    private Level level;

    @NotNull(message = "Department is required")
    private Department department;

}
