package com.shir.hometask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemNotFoundAdvice {
	@ResponseBody
	  @ExceptionHandler(ItemNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String ItemNotFoundHandler(ItemNotFoundException ex) {
	    return ex.getMessage();
	  }
}
