package com.shir.hometask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemInventoryCodeExistAdvice {
	
	@ResponseBody
	  @ExceptionHandler(ItemInventoryCodeExistException.class)
	  @ResponseStatus(HttpStatus.FORBIDDEN)
	  String ItemInventoryCodeExistHandler(ItemInventoryCodeExistException ex) {
	    return ex.getMessage();
	  }

}

