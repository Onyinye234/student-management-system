package com.project.student_manager.controller;

import com.project.student_manager.request.UpdateScoreRequest;
import com.project.student_manager.response.CustomResponse;
import com.project.student_manager.service.RecordService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping({"api/v1/records"})
@Validated
public class RecordController {
    private final RecordService recordService;

    @PatchMapping("{studentId}/{courseId}")
    public ResponseEntity<CustomResponse> setScores(
            @PathVariable(required = true)
                    @NotNull(message = "Student ID cannot be left blank ")
                    @Positive(message = "ID must be positive ")
                    Long studentId,
            @PathVariable(required = true)
                    @NotNull(message = "Course ID cannot be left blank ")
                    @Positive(message = "ID must be positive value")
                    Long courseId,
            @Valid @RequestBody UpdateScoreRequest updateScoreRequest) {
        recordService.UpdateScore(studentId, courseId, updateScoreRequest);
        return ResponseEntity.ok(new CustomResponse("00", "Successfully updated", null));
    }
}
