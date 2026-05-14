package com.project.student_manager.controller;

import com.project.student_manager.response.CustomResponse;
import com.project.student_manager.service.EnrollmentService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping({"api/v1/enrollments"})
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    @PostMapping("{studentId}/{courseId}")
    public ResponseEntity<CustomResponse> enrollStudentForCourse(
            @PathVariable
                    @NotNull(message = "Student ID cannot be left blank ")
                    @Positive(message = "ID must be a positive value")
                    Long studentId,
            @PathVariable
                    @NotNull(message = "Course ID cannot be left blank ")
                    @Positive(message = "ID must be a positive value")
                    Long courseId) {
        enrollmentService.enrollStudentForCourse(studentId, courseId);
        return ResponseEntity.ok(new CustomResponse("00", "Success", null));
    }
}
