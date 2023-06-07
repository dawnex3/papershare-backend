package com.itheima.exception;

import com.itheima.controller.ResponseData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { NotFoundException.class })
    protected ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ResponseData<Object> responseData = new ResponseData<>(null, ex.getMessage(), 404);
        return handleExceptionInternal(ex, responseData, new HttpHeaders(), status, request);
    }
}
