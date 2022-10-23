package com.yashtry.task.dto;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {

    private HttpStatus status;

    private boolean success;

    private Object data;

    private String errorMessage;

    public static ApiResponse ok() {
        ApiResponse response = new ApiResponse();

        response.setStatus(HttpStatus.OK);
        response.setSuccess(true);

        return response;
    }

    public static ApiResponse ok(Object data) {
        ApiResponse response = new ApiResponse();

        response.setStatus(HttpStatus.OK);
        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    public static ApiResponse fail() {

        ApiResponse response = new ApiResponse();

        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setSuccess(false);

        return response;

    }

    public static ApiResponse fail(String errorMessage) {

        ApiResponse response = new ApiResponse();

        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setSuccess(false);
        response.setErrorMessage(errorMessage);

        return response;

    }
}
