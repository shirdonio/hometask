package com.shir.hometask;

class ItemNotFoundException extends RuntimeException{
	
	ItemNotFoundException(Long id) {
		super("Could not find item " + id);
	}

}
