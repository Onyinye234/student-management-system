package com.project.student_manager.request;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import com.project.student_manager.enums.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseUpdateRequest {
    private String courseCode;
    private String courseName;
    private String courseUnit;
    private Semester semester;
    private Level level;
    private Department department;
}
