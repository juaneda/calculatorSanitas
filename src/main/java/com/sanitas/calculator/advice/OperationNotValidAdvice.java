package com.sanitas.calculator.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.sanitas.calculator.exception.OperationNotValid;

@ControllerAdvice
public class OperationNotValidAdvice {

	  @ResponseBody
	  @ExceptionHandler(OperationNotValid.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  String employeeNotFoundHandler(OperationNotValid ex) {
	    return ex.getMessage();
	  }
}
