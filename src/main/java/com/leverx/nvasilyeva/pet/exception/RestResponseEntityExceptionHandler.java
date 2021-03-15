package com.leverx.nvasilyeva.pet.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<IllegalArgumentException> handleIllegalArgumentException(
            IllegalArgumentException exception) {
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    protected ResponseEntity<Object> handleNoSuchElementFoundException(
            NoSuchElementFoundException exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("exception", exception);
        body.put("causeException", exception.getCause());
        body.put("message", "No data found ");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    protected ResponseEntity<NoSuchElementFoundException> handleNoSuchElementFoundException(
            NoSuchElementFoundException exception) {
        return new ResponseEntity<>(new NoSuchElementFoundException("No such element exception. Exception" +
                exception, exception.getCause()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class, HttpServerErrorException.class})
    protected ResponseEntity<String> handleHttpErrorException(
            Exception exception) {
        if (exception instanceof HttpClientErrorException) {
            return ResponseEntity.status(((HttpClientErrorException) exception).getStatusCode()).body("Error on client side");
        } else if (exception instanceof HttpServerErrorException) {
            return ResponseEntity.status(((HttpServerErrorException) exception).getStatusCode()).body("Error on server side");
        }
        return null;
    }


    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<String> handleHttpClientErrorException(
            HttpClientErrorException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body("Error on client side");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
