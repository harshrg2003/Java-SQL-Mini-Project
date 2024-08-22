package com.SQLShoppinglist;
import java.util.*;
public class MainClass {
	
	public static void main(String[] args) {
	    Scanner sc=new Scanner(System.in);
		ShoppingListDB d=new ShoppingListDB();
		while(true)
		{
			System.out.println("Choose among any one of the following options");
			System.out.println("1.Add an item to the Shopping list");
			System.out.println("2.Display all the items in the shopping list");
			System.out.println("3.Delete an item from the shopping list");
			System.out.println("4.Update an item in the ShoppingList");
			System.out.println("5.Search for an item in the shopping list");
			System.out.println("Enter your choice");
			int choice=sc.nextInt();
			switch(choice)
			{
			   case 1:System.out.println("Enter the name of the item to be inserted");
			          String itemName=sc.next();
			          System.out.println("Enter the price of the item:");
			          int price=sc.nextInt();
			          System.out.println("Enter the Quantity of the item:");
			          int quantity=sc.nextInt();
			          System.out.println("Enter the ID of the item:");
			          int id=sc.nextInt();
			          ShoppingList sl=new ShoppingList(id,itemName,price,quantity);
			          d.addItem(sl);
			          break;
			   case 2:System.out.println("*******ShoppingListRecord*************");
			          ArrayList<ShoppingList> al=d.getAllItems();
			          int TotalPrice=0;
			          for(ShoppingList s:al)
			          {
			        	  TotalPrice+=s.getPrice()*(s.getQuantity());
			        	  System.out.println(s);
			          }
			          System.out.println("The total price calculated is"+TotalPrice);
			          break;
			   case 3:System.out.println("Enter the Name for the item to be deleted");
			          itemName=sc.next();
			          d.deleteItem(itemName);
			          break;
			   case 4:System.out.println("Enter the name of the item to update");
			          itemName=sc.next();
			          System.out.println("Enter the new price of the item");
			          price=sc.nextInt();
			          System.out.println("Enter the new Quantity of the item");
			          quantity=sc.nextInt();
			          System.out.println("Enter the ID at which item is to be updated");
			          id=sc.nextInt();
			          sl=new ShoppingList(id,itemName,price,quantity);
			          d.updateItem(sl);
			          break;
			   case 5:System.out.println("Enter the name of the item to be searched");
			          itemName=sc.next();
			          d.searchItem(itemName);
			          break;
			  default:System.out.println("Thank you for using the ShoppingList Application");
			          System.exit(0);
			}
		}

	}

}
