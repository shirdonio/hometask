package com.shir.hometask;

public class BadAmountException extends RuntimeException{
	
	BadAmountException() {
		super("Faild! number must be bigger then 0.");
	}

}
