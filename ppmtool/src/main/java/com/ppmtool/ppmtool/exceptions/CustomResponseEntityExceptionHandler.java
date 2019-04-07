package com.ppmtool.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdExcepion(ProjectIdException ex, WebRequest request){
       ProjectIdExceptionReponse projectIdExceptionReponse=new ProjectIdExceptionReponse(ex.getMessage());
       return new ResponseEntity(projectIdExceptionReponse, HttpStatus.BAD_REQUEST);
    }
}
