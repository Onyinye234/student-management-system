package com.project.student_manager.controller;

import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import com.project.student_manager.request.CourseRegisterationRequest;
import com.project.student_manager.request.CourseUpdateRequest;
import com.project.student_manager.response.CustomResponse;
import com.project.student_manager.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping({"api/v1/courses"})
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CustomResponse> registerCourse(
            @RequestBody @Valid CourseRegisterationRequest courseRegisterationRequest) {
        return ResponseEntity.ok(
                new CustomResponse(
                        "200",
                        "Successful",
                        courseService.registerCourse(courseRegisterationRequest)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse> updateCourse(
            @PathVariable Long id, @RequestBody CourseUpdateRequest coursePatchRequest) {
        return ResponseEntity.ok(
                new CustomResponse(
                        "200", "Successful", courseService.updateCourse(id, coursePatchRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok(
                new CustomResponse("200", "Successful", courseService.deleteCourse(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new CustomResponse("200", "Successful", courseService.getCourseById(id)));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllCourses(
            @RequestParam(required = false) Department department,
            @RequestParam(required = false) Level level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                new CustomResponse(
                        "200",
                        "Successful",
                        courseService.getAllCourses(department, level, page, size)));
    }
}
