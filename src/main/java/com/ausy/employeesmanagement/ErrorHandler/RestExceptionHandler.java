package com.ausy.employeesmanagement.ErrorHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.Set;

@ControllerAdvice
// Adnotarea @ControllerAdvice vă permite să gestionați excepții în întreaga aplicație, nu doar la un controller individual. Vă puteți gândi la acesta ca la un interceptor al excepțiilor aruncate prin metode adnotate cu @RequestMapping(GET, PUT, POST, DELETE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {




    @ExceptionHandler(Exception.class)  // Definim un handler pentru o excepție generică
    public ResponseEntity<Object> hendleGenericException(Exception e , HttpServletResponse response)
    {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        response.setStatus(httpStatus.value());
        return buildResponseEntity(new ApiError(httpStatus,"Internal Server Error: Unexpected Exception",e));
    }



    @ExceptionHandler({EmployeeNotFoundException.class})  //Definim un handler pentru a prinde exceptia UserNotFoundException (în cazul nostru dacă ID-ul căutat nu se află în baza de date)
    public ResponseEntity<Object> handleEntityNotFound(EmployeeNotFoundException ex, HttpServletResponse response)
    {
        HttpStatus httpStatus =HttpStatus.NOT_FOUND;
        return buildResponseEntity(new ApiError(httpStatus,ex.toString(),ex));
    }

    @ExceptionHandler({DepartmentNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFound(DepartmentNotFoundException ex, HttpServletResponse response)
    {
        HttpStatus httpStatus =HttpStatus.NOT_FOUND;
        return buildResponseEntity(new ApiError(httpStatus,ex.toString(),ex));
    }

    @ExceptionHandler({JobCategoryNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFound(JobCategoryNotFoundException ex, HttpServletResponse response)
    {
        HttpStatus httpStatus =HttpStatus.NOT_FOUND;
        return buildResponseEntity(new ApiError(httpStatus,ex.toString(),ex));
    }



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }


    @Override //  Aici suprascriem metoda care se ocupa de  maparea   corecta a verbelor HTTP .(de exemplu daca  vrem sa facem un POST pe un URL de GET , aceasta metoda va arunca raspunsul customizat de noi , nu cel default!)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_GATEWAY,"HTTP verb unexpected",ex));
    }




    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) // Metodă care creează răspunsul (bazat pe clasa ApiError) în caz de eroare
    {
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }


}
