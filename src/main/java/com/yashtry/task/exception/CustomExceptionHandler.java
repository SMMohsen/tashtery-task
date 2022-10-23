package com.yashtry.task.exception;

import com.yashtry.task.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleValidationException(CustomValidationException exception) {

        return ApiResponse.fail(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleArgumentNotValidException(MethodArgumentNotValidException e) {

        return ApiResponse.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleUnexpectedException(Exception e) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setErrorMessage(e.getMessage());
        apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiResponse.setSuccess(false);

        return apiResponse;
    }
}
