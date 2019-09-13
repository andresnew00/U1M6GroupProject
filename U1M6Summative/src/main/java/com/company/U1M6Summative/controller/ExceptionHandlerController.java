package com.company.U1M6Summative.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionHandlerController {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

    public ResponseEntity<VndErrors> outOfRangeException(IllegalArgumentException e, WebRequest webRequest) {
        VndErrors errors = new VndErrors(webRequest.toString(), e.getMessage());
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    // methodArgumentNotValid
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<VndErrors> notEnoughValues(MethodArgumentNotValidException e, WebRequest request) {
        //Binding result holds the validation result
        BindingResult result = e.getBindingResult();
        //validation errors are stored in fieldError object
        List<FieldError> fieldErrors = result.getFieldErrors();
        //translate into Vnd errors
        List<VndErrors.VndError> vndErrorList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            VndErrors.VndError vndError = new VndErrors.VndError(request.toString(), fieldError.getDefaultMessage());
            vndErrorList.add(vndError);
        }
        VndErrors errors = new VndErrors(vndErrorList);

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
