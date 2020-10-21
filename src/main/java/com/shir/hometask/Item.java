package com.shir.hometask;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
class Item {

  private @Id @GeneratedValue Long id;
  private String name;
  private int amount;
  private String inventoryCode;

  Item() {}

  Item(String name, int amount, String inventoryCode) {

    this.name = name;
    this.amount = amount;
    this.inventoryCode = inventoryCode;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
	this.id = id;
}

public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
	    this.name = name;
  }

  public int getAmount() {
	return amount;
  }

  public void setAmount(int amount) {
	this.amount = amount;
  }

  public String getInventoryCode() {
	return inventoryCode;
  }

  public void setInventoryCode(String inventoryCode) {
	this.inventoryCode = inventoryCode;
  }


  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Item))
      return false;
    Item item = (Item) o;
    return Objects.equals(this.id, item.id) && Objects.equals(this.name, item.name)
    		&& Objects.equals(this.amount, item.amount) && Objects.equals(this.inventoryCode, item.inventoryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.amount, this.inventoryCode);
  }

  @Override
  public String toString() {
    return "Item{" + "id=" + this.id + ", name='" + this.name + '\'' + ", amount='" + this.amount + '\'' + ", inventory code='" + this.inventoryCode + '\'' + '}';
  }


}