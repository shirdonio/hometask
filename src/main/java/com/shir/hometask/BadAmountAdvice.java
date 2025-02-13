package com.shir.hometask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadAmountAdvice {
	@ResponseBody
	  @ExceptionHandler(BadAmountException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  String BadAmounttHandler(BadAmountException ex) {
	    return ex.getMessage();
	  }

}
	
