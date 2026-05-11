package com.project.student_manager.exceptions;

import com.project.student_manager.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentManagerException extends RuntimeException {
  private ErrorType errorType;
  private String message;
}
