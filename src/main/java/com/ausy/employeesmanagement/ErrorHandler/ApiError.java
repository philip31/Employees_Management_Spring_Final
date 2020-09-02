package com.ausy.employeesmanagement.ErrorHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError { // clasa ApiError
    private HttpStatus httpStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private ApiError()
    {
        this.timestamp=LocalDateTime.now();
    }
    public ApiError(HttpStatus httpStatus)
    {
        this(); // call private constructor!
        this.httpStatus=httpStatus;
    }
    public ApiError(HttpStatus httpStatus, String message, Throwable exception)
    {
        this();
        this.httpStatus=httpStatus;
        this.message=message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
