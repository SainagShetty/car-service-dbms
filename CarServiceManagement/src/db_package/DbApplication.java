package db_package;
import java.sql.*;
import java.util.*;

public class DbApplication {
	static Scanner reader;
	static Connection con;
	
	public static void main(String args[]){ 
		DBConnection dbConnection = DBConnection.getDBConnection();
		try{    
			con = dbConnection.createConnection();
			reader = new Scanner(System.in);
			try {
				while(true) {
					System.out.println("Select 1. Login 2. SignUp");
					String input = reader.nextLine();
					if (input.startsWith("1")) {
						loginPage();
					} else if (input.startsWith("2")) {
						signUpPage();
						
					} else {
						System.out.println("invalid Input. Try again!");
					}
				}
				
			} catch (Exception e) {
				System.exit(0);
			}
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		} 
	
	
	public static void loginPage() {
		Boolean status = false;
		Boolean signup = false;
		Person p = new Person(con);
		
		while(!status) {
			System.out.println("Enter UserID");
			String userid = reader.nextLine();
			
			System.out.println("Enter Password");
			String password = reader.nextLine();
			status = p.login(userid, password);
		
			if(status == false) {
				System.out.println(" Invalid Credentials. enter 1 to goto signup; 0 to try again");
				String input = reader.nextLine();
				
				if (input.startsWith("1")) {
					signup =  true;
					break;
				}
				if (input.startsWith("2")) 
					continue;		
			}
		}
		
		if (signup == true) signUpPage();
		// old user
		System.out.println(p.my_role);
		switch (p.my_role) {
		case Role.MANAGER: {
			Manager manLogIn = new Manager(p, con);
			manLogIn.managerMenu();
		};
		case Role.CUSTOMER:
			Customer cusLogIn = new Customer(p, con);
			cusLogIn.customerMenu();
			
		case Role.RECEPTIONIST:
			Receptionist respLogIn = new Receptionist(p, con);
			respLogIn.ReceptionistMenu();
			
			
		case Role.MECHANIC:
			
		}
			
		
		
		
	}
	// only customer signUp
	public static void signUpPage() {
		System.out.println("###Register a New Customer");
		System.out.println("Enter EmailAddress");
		String c_email = reader.nextLine();
		System.out.println("Enter Password");
		String c_password = reader.nextLine();
		System.out.println("Enter Name");
		String c_name = reader.nextLine();
		System.out.println("Enter Address");
		String c_add = reader.nextLine();
		System.out.println("Enter PhoneNumber");
		String c_tel_no = reader.nextLine();
		System.out.println("Enter ServiceCenter id");
		String sc_id = reader.nextLine();
		//userid = email
		Customer cus = new Customer(c_email, c_email, c_password , c_name, c_add, c_tel_no, Integer.parseInt(sc_id), con);
		System.out.println("### Customer Created. Login with new credentials");
		loginPage();	
	}
	
}
