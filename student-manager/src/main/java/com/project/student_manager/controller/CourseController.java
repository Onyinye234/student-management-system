package com.project.student_manager.controller;

import com.project.student_manager.request.CourseRegisterationRequest;
import com.project.student_manager.response.CustomResponse;
import com.project.student_manager.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping({ "api/v1/courses" })
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CustomResponse> registerCourse(
        @RequestBody @Valid CourseRegisterationRequest courseRegisterationRequest
    ) {
        return ResponseEntity.ok(
            new CustomResponse(
                "00",
                "Successful",
                courseService.registerCourse(courseRegisterationRequest)
            )
        );
    }
}
