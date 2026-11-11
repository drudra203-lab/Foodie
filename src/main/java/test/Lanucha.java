package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.List;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import com.foodie.daoImple.MenuDaoImpl;
//import com.foodie.daoImple.RestaurantDaoImpl;
import com.foodie.daoImple.UserDaoImpl;
import com.foodie.dbconnection.DBConnection;
import com.foodie.model.MenuItem;
//import com.foodie.model.Restaurant;
import com.foodie.model.User;


public class Lanucha {

	private static final String GET_MENU_BY_MENUID = "SELECT * FROM `menuitems` WHERE `id`=?";
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
	System.out.println("ENTER YOUR ID: ");
	int id = scan.nextInt();		
	
	MenuDaoImpl menuDaoImpl = new MenuDaoImpl();

    MenuItem menuItem = menuDaoImpl.getMenuItemById(id);

    System.out.println("Menu Item: " + menuItem);

 if (menuItem == null) {
       System.out.println("No menu item found for ID: " + id);
   return;
    }
		
		
//		System.out.println("ENTER YOUR NAME: ");
//		String username = scan.next();
//		System.out.println("ENTER YOUR EMAIL: ");
//		String email = scan.next();
//		System.out.println("ENTER YOUR PASSWORD: ");
//		String password = scan.next();
//		System.out.println("ENTER YOUR ADDRES: ");
//		String address = scan.next();
//	
//		System.out.println("ENTER YOUR ROLE: ");
//		String role = scan.next();
		
		
        //String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
//		User user = new User(username,email,password,address);
//		
//		UserDaoImpl userDaoImpl = new UserDaoImpl();
//		int rowAffeccted = userDaoImpl.signup(user);
//		System.out.println(rowAffeccted);
		

	
	    
	    		
	}
	
}
