package com.SQLShoppinglist;
import java.sql.*;
import java.util.*;
public class ShoppingListDB {
   private Connection conn;
   Scanner sc=new Scanner(System.in);
   private static final String username="root";
   private static final String password="T2h%4A8$534";
   private static final String URL="jdbc:mysql://localhost/shoppinglistdemo";
    public ShoppingListDB()
    {
    	int count=0;
    	boolean correctinfo=false;
    	while(count<3)
    	{
    	  System.out.println("Enter your username and password to establish connection to MySQLDatabase");
    	  String username1=sc.next();
    	  String password1=sc.next();
    	  if(username1.equals(username)&& password1.equals(password))
    	  {	
    	     try
    	     {
    		    Class.forName("com.mysql.cj.jdbc.Driver");
    		    conn=DriverManager.getConnection(URL,username,password);
    	     }
    	     catch(ClassNotFoundException |SQLException e)
    	     {
    		   e.printStackTrace();
    	     }
    	     correctinfo=true;
    	     break;
    	  }
    	  else
    	  {
    		  count++;
    		  continue;
    	  }
       }
    	if(correctinfo==false)
    	{
    		System.out.println("OOPS!!Program termination due to incorrect username and password 3 times");
			  System.exit(0);
    	}
    }
    public ArrayList<ShoppingList> getAllItems()
    {
    	ArrayList<ShoppingList> items=new ArrayList<ShoppingList>();
    	try
    	{
    		Statement stmt=conn.createStatement();
    		ResultSet rs=stmt.executeQuery("SELECT * FROM shoppinglist");
    		while(rs.next())
    		{
    			int id=rs.getInt("Item_id");
    			String name=rs.getString("Item_name");
    			int price=rs.getInt("Price");
    			int quantity=rs.getInt("Quantity");
    			items.add(new ShoppingList(id,name,price,quantity));
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return items;
    }
    public void addItem(ShoppingList sl)
    {
    	try
    	{
    		PreparedStatement stmpt=conn.prepareStatement("INSERT into shoppinglist(Item_id,Item_name,Price,Quantity)VALUES(?,?,?,?)");
    		stmpt.setInt(1, sl.getID());
    		stmpt.setString(2, sl.getName());
    		stmpt.setDouble(3, sl.getPrice());
    		stmpt.setInt(4, sl.getQuantity());
    		int res=stmpt.executeUpdate();
    		if(res>0)
    		{
    			System.out.println("Record inserted succesfully");
    		}
    		else
    		{
    			System.out.println("Not successfull insertion");
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void updateItem(ShoppingList sl)
    {
    	try
    	{
    	  PreparedStatement stmpt=conn.prepareStatement("UPDATE shoppinglist SET Item_name=?,Price=?,Quantity=? WHERE Item_id=?");
    	  stmpt.setString(1, sl.getName());
    	  stmpt.setDouble(2, sl.getPrice());
    	  stmpt.setInt(3, sl.getQuantity());
    	  stmpt.setInt(4, sl.getID());
    	  int res=stmpt.executeUpdate();
    	  if(res>0)
    	  {
    		  System.out.println("Record updated succesfully");
    	  }
    	  else
    	  {
    		  System.out.println("Sorry!Could not update");
    	  }
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void deleteItem(String itemName)
    {
    	try
    	{
    	   PreparedStatement stmpt=conn.prepareStatement("DELETE FROM shoppinglist WHERE Item_Name=?");
    	   stmpt.setString(1, itemName);
    	   int res=stmpt.executeUpdate();
    	   if(res>0)
    	   {
    		   System.out.println("Item deleted succesfully");
    	   }
    	   else
    	   {
    		   System.out.println("Deletion not succesfull");
    	   }
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    public void searchItem(String itemName)
    {
    	try
    	{
    		PreparedStatement stmpt=conn.prepareStatement("SELECT * FROM shoppinglist WHERE Item_name=?");
    		stmpt.setString(1, itemName);
    		ResultSet rs=stmpt.executeQuery();
    		if(rs.next())
    		{
    			int id=rs.getInt("Item_id");
    			int price=rs.getInt("Price");
    			int quantity=rs.getInt("Quantity");
    			ShoppingList sl=new ShoppingList(id,itemName,price,quantity);
    			System.out.println(sl);
    		}
    		else
    		{
    			System.out.println("Item not found in database");
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    
}
