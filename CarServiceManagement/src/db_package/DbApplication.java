package db_package;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DbApplication {
	static Scanner reader;
	static Connection con;

  static ArrayList<String> ServiceCIDList;
	
	public static void main(String args[]){ 
	   ServiceCIDList = new ArrayList<String>();
	   ServiceCIDList.add("S0001");
	   ServiceCIDList.add("S0002");
		DBConnection dbConnection = DBConnection.getDBConnection();
		try{    
			con = dbConnection.createConnection();
			reader = new Scanner(System.in);
			try {
				while(true) {
					System.out.println("Select 1. Login 2. SignUp 3. Exit");
					String input = reader.nextLine().trim();
					if (input.startsWith("1")) {
						loginPage();
					} else if (input.startsWith("2")) {
						signUpPage();
					} else if (input.startsWith("3")) {
						dbConnection.closeConnection();
						System.out.println("Closing Connections...");
						System.out.println("Closing Application...");
						System.exit(0);
					}
					else {
						System.out.println("invalid Input. Try again!");
					}
				}
				
			} catch (Exception e) {
				dbConnection.closeConnection();
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
			String userid = reader.nextLine().trim();
			
			System.out.println("Enter Password");
			String password = reader.nextLine().trim();
			status = p.login(userid, password);
		
			if(status == false) {
				System.out.println(" Invalid Credentials. enter 1 to goto signup; 0 to try again");
				String input = reader.nextLine().trim();
				
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
//		System.out.println(p.my_role);
		switch (p.my_role) {
		case Role.MANAGER: 
			Manager manLogIn = new Manager(p, con);
			manLogIn.managerMenu();	
			break;
		case Role.CUSTOMER:
			System.out.println("Customer object created\n");
			Customer cusLogIn = new Customer(p, con);
			cusLogIn.customerMenu();
			break;
		case Role.RECEPTIONIST:
			Receptionist respLogIn = new Receptionist(p, con);
			respLogIn.ReceptionistMenu();
			break;
		case Role.MECHANIC:
			
		}
			
		
		
		
	}
	// only customer signUp
	public static void signUpPage() {
		System.out.println("###Register a New Customer");
		System.out.println("Enter EmailAddress");
		String c_email = reader.nextLine().trim();
		System.out.println("Enter Password");
		String c_password = reader.nextLine().trim();
		System.out.println("Enter Name");
		String c_name = reader.nextLine().trim();
		System.out.println("Enter Address");
		String c_add = reader.nextLine().trim();
		System.out.println("Enter PhoneNumber");
		String c_tel_no = reader.nextLine().trim();
		System.out.println("Enter ServiceCenter id");
		String sc_id = reader.nextLine().trim();
		//userid = email
		Customer cus = new Customer(c_email, c_email, c_password , c_name, c_add, c_tel_no, sc_id, con);
		System.out.println("### Customer Created. Login with new credentials");
		loginPage();	
	}
	
}
