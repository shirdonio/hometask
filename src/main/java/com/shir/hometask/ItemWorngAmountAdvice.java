package com.shir.hometask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemWorngAmountAdvice {
	@ResponseBody
	  @ExceptionHandler(ItemWorngAmountException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  String ItemWorngAmountHandler(ItemWorngAmountException ex) {
	    return ex.getMessage();
	  }

}