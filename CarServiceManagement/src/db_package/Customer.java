package db_package;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

class Customer extends Person{

	Scanner reader;
	List<Integer> vehicleList;
	String c_name;
	String c_address;
	String c_tel_no;
	int c_id;
	int serviceCen_id;
	Customer(Person p, Connection conn){
		super(p);
		//get other details from database
	}

	Customer(int c_id, Connection conn){
	super(conn); //just updates connection 
	// Query 1 fetch from Customer table and update instance variables.
	// Query 2 fetch from persons table and set Persons instance variable.
	}
	
    Customer(String userID,String emailID, String password, String c_name, String c_address, String c_tel_no, int serviceCen_id, Connection conn) {	
		super(emailID, Role.CUSTOMER, conn); // this will create an entry in persons table
		this.c_address = c_address;
		this.c_tel_no = c_tel_no;
		this.c_name = c_name;
		this.serviceCen_id = serviceCen_id;
		createCustomer(conn);
	}
    
    void customerMenu() {
    	Boolean exit = false;
    	while(!exit) {
    		System.out.println("### Customer landing page ###");
    		System.out.println("1.  Profile");
    		System.out.println("2.  Register Car");
    		System.out.println("3.  Service");
    		System.out.println("4.  Invoices");
    		System.out.println("5.  Logout");
    		
    		String input = reader.nextLine();
		if (input.startsWith("1")) {
			this.profilePage();
		} else if (input.startsWith("2")) {
			CarRegister cr = new CarRegister(Role.CUSTOMER, this.conn);
			cr.registerCar(this.c_id);
		} else if (input.startsWith("3")){
			this.servicePage();
		}else if (input.startsWith("4")){
			this.invoicePage();
		}else if (input.startsWith("5")){
			signout();
			exit = true;
		} else {
			exit = true;
		}
    }
    		
    }
    void createCustomer(Connection conn) {
    		// create entry in table.
    }
    private void profilePage() {
    		System.out.println("Print Profile");
    }
//    private void registerCarPage() {
//    	System.out.println("Register Car");
//    	
//    	
//    }
    private void servicePage() {
    		System.out.println("Start a service");
    		
    		
    		
    }
    private void invoicePage() {
    		System.out.println("View Invoice");
    }
    
    
    
    void deleteCustomer(Connection conn) {
    		
		personDelete();
    }

}