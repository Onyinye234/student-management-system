package com.project.student_manager.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomResponse {
    private String responseCode;
    private String responseMessage;
    private Object responseData;
}
