package db_package;

import java.sql.Connection;
import java.util.List;

class Customer extends Person{

	
	List<Integer> vehicleList;
	
	String c_address;
	String c_tel_no;
	

	Customer(int c_id, Connection conn){
	super(conn); //just updates connection 
	// Query 1 fetch from Customer table and update instance variables.
	// Query 2 fetch from persons table and set Persons instance variable.
	}
	
    Customer(String userID,String emailID, String password, String c_address, String c_tel_no, Connection conn) {	
		super(emailID, Role.CUSTOMER, conn); // this will create an entry in persons table
		this.c_address = c_address;
		this.c_tel_no = c_tel_no;
		createCustomer(conn);
	}
    
    
    void createCustomer(Connection conn) {
    		// create entry in table.
    }
    void deleteCustomer(Connection conn) {
    	
		personDelete();
    }

}