package com.project.student_manager.controller;

import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.response.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<CustomResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex
    ) {
        var fieldError = ex.getBindingResult().getFieldError();
<<<<<<< HEAD
        String message =(fieldError != null) ? fieldError.getDefaultMessage() : "Validation Error occurred";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse("08",message,null));

    }
    @ExceptionHandler({StudentManagerException.class})
    public ResponseEntity<CustomResponse>handleStudentManagerException(StudentManagerException ex){

     String code = getResponseCode(ex);
     HttpStatus status = getHttpStatus(ex);

     return ResponseEntity.status(status).body(new CustomResponse(code,ex.getMessage(),null));

    }

    public HttpStatus getHttpStatus(StudentManagerException ex){
        return switch(ex.getErrorType()){
            case STUDENT_ALREADY_REGISTERED,COURSE_ALREADY_REGISTERED -> HttpStatus.CONFLICT;
            default->HttpStatus.INTERNAL_SERVER_ERROR;

        };
    }


    public String getResponseCode(StudentManagerException ex){
        return switch(ex.getErrorType()){

=======
        String message = (fieldError != null)
            ? fieldError.getDefaultMessage()
            : "Validation Error occurred";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new CustomResponse("08", message, null)
        );
    }

    @ExceptionHandler({ StudentManagerException.class })
    public ResponseEntity<CustomResponse> handleStudentManagerException(
        StudentManagerException ex
    ) {
        String code = getResponseCode(ex);
        HttpStatus status = getHttpStatus(ex);

        return ResponseEntity.status(status).body(
            new CustomResponse(code, ex.getMessage(), null)
        );
    }

    public HttpStatus getHttpStatus(StudentManagerException ex) {
        return switch (ex.getErrorType()) {
            case
                STUDENT_ALREADY_REGISTERED,
                COURSE_ALREADY_REGISTERED -> HttpStatus.CONFLICT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    public String getResponseCode(StudentManagerException ex) {
        return switch (ex.getErrorType()) {
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
            case STUDENT_ALREADY_REGISTERED -> "01";
            case COURSE_ALREADY_REGISTERED -> "02";
            default -> "05";
        };
    }
}
