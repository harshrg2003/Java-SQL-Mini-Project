package com.SQLShoppinglist;

public class ShoppingList {
    private int id;
    private String name;
    private int price;
    private int quantity;
      public ShoppingList(int id,String name,int price,int quantity)
      {
    	  this.id=id;
    	  this.name=name;
    	  this.price=price;
    	  this.quantity=quantity;
      }
      public int getID()
      {
    	  return id;
      }
      public String getName()
      {
    	  return name;
      }
      public int getPrice()
      {
    	  return price;
      }
      public int getQuantity()
      {
    	  return quantity;
      }
      public String toString()
      {
    	  return ("Item ID: "+id+"\tItem Name: "+name+"\tPrice: "+price+"\tQuantity: "+quantity);
      }
}
