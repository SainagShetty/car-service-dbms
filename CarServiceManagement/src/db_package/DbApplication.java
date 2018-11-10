package src.db_package;
import java.sql.*;
import java.util.*;

public class DbApplication {
	static Scanner reader;
	static Connection con;
	public static void main(String args[]){  
		try{    
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			    
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01","rdange","200259721");
//			Connection con=DriverManager.getConnection(  
//					"jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01","sgshetty","200203904");  
	  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from Test"); 

//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//			while(rs.next())  {
//			    for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
//			        Object object = rs.getObject(columnIndex);
//			        System.out.printf("%s, ", object == null ? "NULL" : object.toString());
//			    }
//			    System.out.printf("%n");
//			}
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
			
//            con.commit();
//			con.close(); 
		  
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
		
		//userid = email
		Person cus = new Customer(c_email, c_email, c_password , c_name, c_add, c_tel_no, con);
		
		
		
	}
	
}
