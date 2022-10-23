package com.yashtry.task.exception;

import lombok.Data;

@Data
public class CustomValidationException extends RuntimeException {


    public CustomValidationException(String errorMessage) {

        super(errorMessage);
    }

}
