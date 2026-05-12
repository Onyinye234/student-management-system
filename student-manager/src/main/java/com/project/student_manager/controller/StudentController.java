package com.project.student_manager.controller;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import com.project.student_manager.request.StudentRegistrationRequest;
import com.project.student_manager.request.UpdateStudentDetailsRequest;
import com.project.student_manager.response.CustomResponse;
import com.project.student_manager.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated

@AllArgsConstructor
@RestController
@RequestMapping({ "api/v1/students" })
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


    @GetMapping("/{studentId}")
    public ResponseEntity<CustomResponse> getStudent(@PathVariable @NotNull(message = "ID field cannot be left empty") @Positive(message = "ID must be a positive value") Long studentId){
        return ResponseEntity.ok(
                new CustomResponse("00", "Successful", studentService.getStudentAndMapToDto(studentId)));
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<CustomResponse> updateStudentDetails(@PathVariable @NotNull(message = "ID field cannot be null")@Positive(message = "ID ust be a positive value") Long studentId, @RequestBody @Valid UpdateStudentDetailsRequest updateStudentDetailsRequest){
        return ResponseEntity.ok(
                new CustomResponse("00", "Successful", studentService.updateStudentDetails(studentId, updateStudentDetailsRequest))
        );
    }


@GetMapping("/allStudents")
    public ResponseEntity<CustomResponse> getAllStudents(@RequestParam int page,
                                                               @RequestParam int size,
                                                               @RequestParam(required = false) Level level,
                                                               @RequestParam(required = false) Department department



                                                               ){
         return ResponseEntity.ok(new CustomResponse("00", "Successful", studentService.findAllStudents(department,level,size, page)));

}
}
