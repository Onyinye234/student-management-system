package com.project.student_manager.dto;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import com.project.student_manager.enums.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {

    private Long courseId;
    private String courseCode;
    private String courseName;
    private String courseUnit;
    private Semester semester;
    private Level level;
    private Department department;
}
