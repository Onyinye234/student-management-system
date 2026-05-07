package com.project.student_manager.dto;

import com.project.student_manager.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {

    private String courseCode;
    private String courseName;
    private String courseUnit;
    private String semester;
    private Level level;
}
