package com.project.student_manager.controller;

import com.project.student_manager.request.StudentRegistrationRequest;
import com.project.student_manager.response.CustomResponse;
import com.project.student_manager.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping({ "api/v1/student" })
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<CustomResponse> registerStudent(
        @RequestBody @Valid StudentRegistrationRequest studentRegistrationRequest
    ) {
        return ResponseEntity.ok(
            new CustomResponse(
                "200",
                "Successful",
                studentService.enrollStudent(studentRegistrationRequest)
            )
        );
    }
}
