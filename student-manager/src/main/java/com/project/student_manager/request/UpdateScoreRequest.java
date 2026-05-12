package com.project.student_manager.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateScoreRequest {
    @PositiveOrZero(message = "Scores must be positive or zero")
    @DecimalMax(value = "40.0", message = "CA is over 40")
    private Float ca;

    @PositiveOrZero(message = "Scores must be positive or zero")
    @DecimalMax(value = "60.0", message = "Exam is over 60")
    private Float exam;
}
