package com.comparus.controller;

import com.comparus.dto.response.ErrorResponse;
import com.comparus.exception.MultithreadingTaskFailedException;
import com.comparus.exception.TargetDataSourceDoesNotDefinedException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Hidden
@ResponseBody
@ControllerAdvice
public class DefaultControllerAdvice {

    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorResponse> httpMediaTypeNotAcceptableExceptionHandler(
            HttpServletRequest request, HttpMediaTypeNotAcceptableException exception) {
        log.warn(buildExceptionLog("Handle HttpMediaTypeNotAcceptableException", request), exception);
        ErrorResponse response = createErrorResponse(NOT_ACCEPTABLE, "Unsupported media type", request);
        return ResponseEntity.status(NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse httpMethodNotSupportedExceptionHandler(HttpServletRequest request,
                                                                HttpRequestMethodNotSupportedException exception) {
        log.warn(buildExceptionLog("Handle HttpRequestMethodNotSupportedException", request), exception);
        return createErrorResponse(METHOD_NOT_ALLOWED, exception.getMessage(), request);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MultithreadingTaskFailedException.class)
    public ErrorResponse multithreadingTaskFailedExceptionHandler(
            HttpServletRequest request, MultithreadingTaskFailedException exception) {
        log.warn(buildExceptionLog("Handle MultithreadingTaskFailedException", request), exception);
        return createErrorResponse(INTERNAL_SERVER_ERROR, exception.getMessage(), request);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TargetDataSourceDoesNotDefinedException.class)
    public ErrorResponse targetDataSourceDoesNotDefinedExceptionHandler(
            HttpServletRequest request, TargetDataSourceDoesNotDefinedException exception) {
        log.warn(buildExceptionLog("Handle TargetDataSourceDoesNotDefinedException", request), exception);
        return createErrorResponse(INTERNAL_SERVER_ERROR, exception.getMessage(), request);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse generalThrowableHandler(
            HttpServletRequest request, Throwable throwable) {
        log.warn(buildExceptionLog("Handle general Throwable", request), throwable);
        return createErrorResponse(INTERNAL_SERVER_ERROR, throwable.getMessage(), request);
    }

    private String buildExceptionLog(String message, HttpServletRequest request) {
        return message +
                "\nPath: " +
                request.getRequestURI() +
                "\nMethod: " +
                request.getMethod();
    }


    //Dima
    private ErrorResponse createErrorResponse(HttpStatus status, String message, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();
    }
}
