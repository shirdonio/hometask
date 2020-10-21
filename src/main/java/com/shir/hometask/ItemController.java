package com.shir.hometask;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ItemController {
	
	private final ItemRepository repository;
	private final ItemModelAssembler assembler;

	ItemController(ItemRepository repository, ItemModelAssembler assembler) {
	    this.repository = repository;
	    this.assembler = assembler;
	  }

	  // Aggregate root

	  @GetMapping("/items")
	  CollectionModel<EntityModel<Item>> all() {
		  	
		  List<EntityModel<Item>> items = repository.findAll().stream()
				  .map(assembler::toModel)
			      .collect(Collectors.toList());

			  return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
	  }

	  // Single item

	  @GetMapping("/items/{id}")
	  EntityModel<Item> one(@PathVariable Long id) {
		  Item item = repository.findById(id) 
			      .orElseThrow(() -> new ItemNotFoundException(id));

			  return assembler.toModel(item);
	  }
	  
	  @PostMapping("/items")
	  ResponseEntity<?> newItem(@RequestBody Item newItem) {
		  ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
			      .withMatcher("inventoryCode", ExampleMatcher.GenericPropertyMatchers.exact());
		  Example<Item> example = Example.of(new Item(null, 0, newItem.getInventoryCode()),customExampleMatcher);
		  Optional<Item> actual = repository.findOne(example);
		  if(actual.isPresent()) {
			  throw new ItemInventoryCodeExistException();
		  }else {
			  EntityModel<Item> entityModel = assembler.toModel(repository.save(newItem));
			  return ResponseEntity 
				      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				      .body(entityModel);  
		  }
	  }

//	  @PutMapping("/items/{id}")
//	  ResponseEntity<?> replaceItem(@RequestBody Item newItem, @PathVariable Long id) {
//
//		  Item updatedItem = repository.findById(id)
//			.map(item -> {
//	    	  item.setName(newItem.getName());
//	    	  item.setAmount(newItem.getAmount());
//	    	  return repository.save(item);
//			})
//			.orElseGet(() -> {
//	    	  newItem.setId(id);
//	    	  return repository.save(newItem);
//			});
//		  EntityModel<Item> entityModel = assembler.toModel(updatedItem);
//
//		  return ResponseEntity 
//		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//		      .body(entityModel);
//	  }
	  
	  @PutMapping("/items/{id}/withdrawal")
	  ResponseEntity<?> itemWithdrawal(@RequestBody Amount newAmount, @PathVariable Long id) {

		  int amount = newAmount.getAmount();
		  Item updatedItem = repository.findById(id)
			.map(item -> {
				if(amount < 0) {
					throw new BadAmountException();
				}
				if(item.getAmount()>= amount) {
					item.setAmount(item.getAmount()-amount);
			    	  return repository.save(item);
				}else {
					throw new ItemWorngAmountException();
				}
			}).get();
		  EntityModel<Item> entityModel = assembler.toModel(updatedItem);

		  return ResponseEntity 
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		      .body(entityModel);
	  }
	  
	  
	  @PutMapping("/items/{id}/deposit")
	  ResponseEntity<?> itemDeposit(@RequestBody Amount newAmount, @PathVariable Long id) {
		  
		  int amount = newAmount.getAmount();
		  Item updatedItem = repository.findById(id)
			.map(item -> {
				if (amount < 0) {
					throw new BadAmountException();
				}else {
					item.setAmount(item.getAmount()+amount);
					return repository.save(item);
				}
				}).get();
		  EntityModel<Item> entityModel = assembler.toModel(updatedItem);

		  return ResponseEntity  
		      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		      .body(entityModel);
	  }

	  @DeleteMapping("/items/{id}")
	  ResponseEntity<?> deleteItem(@PathVariable Long id) {
	    repository.deleteById(id);
	    return ResponseEntity.noContent().build();
	  }

}
