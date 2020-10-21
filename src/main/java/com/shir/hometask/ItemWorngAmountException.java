package com.shir.hometask;

public class ItemWorngAmountException extends RuntimeException{
	
	ItemWorngAmountException() {
		super("faild withdrawal! number too big! please try again");
	}

}
