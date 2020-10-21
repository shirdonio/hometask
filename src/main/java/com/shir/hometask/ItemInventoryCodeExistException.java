package com.shir.hometask;

public class ItemInventoryCodeExistException extends RuntimeException {
	
	ItemInventoryCodeExistException() {
		super("Item with this Inventory Code is alredy exist! please enter new Inventory Code");
	}

}
