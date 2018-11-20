package db_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

class Customer extends Person{

	Scanner reader;
	Vector<Vehicle> vehicleList; 
	String c_name;
	String c_address;
	String c_tel_no;
	String c_id;
	String service_center;
	Connection con;
	Vector<InvoicePage> invoices;
	ArrayList<String> srID;
	
	Customer(Person p, Connection conn){
		super(p);
		con = conn;
		reader = new Scanner(System.in);
		vehicleList = new Vector<Vehicle>();
		//get other details from database
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT c_id, c_name, sc_id, c_addr, c_tel_no FROM Customer WHERE c_email=?");
			pstmt.setString(1, p.emailID);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.c_id = rs.getString(1);
				this.c_name = rs.getString(2); 
				this.service_center = rs.getString(3); 
				this.c_address = rs.getString(4);
				this.c_tel_no = rs.getString(5);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try{
			pstmt2 = conn.prepareStatement("SELECT license_no FROM Vehicle WHERE c_id=?");
			pstmt2.setString(1, this.c_id);
			rs2 = pstmt2.executeQuery();
			while(rs2.next())  {
				String tmp = rs2.getString(1);
				Vehicle v = new Vehicle(tmp);
				vehicleList.add(v);
//				System.out.println(tmp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
 
	
	Customer(String email, Connection conn){		
		super(conn); //just updates connection
		this.con = conn;
		this.emailID = email;
		PreparedStatement pstmt = null;
		vehicleList = new Vector<Vehicle>();
		ResultSet rs = null;
		// Query 1 fetch from Customer table and update instance variables.
		try{
			pstmt = conn.prepareStatement("SELECT c_id, c_name, sc_id, c_addr, c_tel_no FROM Customer WHERE c_email=?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.c_id = rs.getString(1);
				this.c_name = rs.getString(2); 
				this.service_center = rs.getString(3); 
				this.c_address = rs.getString(4);
				this.c_tel_no = rs.getString(5);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try{
			pstmt2 = conn.prepareStatement("SELECT license_no FROM Vehicle WHERE c_id=?");
			pstmt2.setString(1, this.c_id);
			rs2 = pstmt2.executeQuery();
			System.out.println("qury executed");
			while(rs2.next())  {
				String tmp = rs2.getString(1);
				System.out.println("Inside while " +tmp);
				Vehicle v = new Vehicle(tmp);
				vehicleList.add(v);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		System.out.println("End of Customer Constructor");
	// Query 2 fetch from persons table and set Persons instance variable.
		
	}
	
	// signup
    Customer(String userID,String emailID, String password, String c_name, String c_address, String c_tel_no, String serviceCen_id, Connection conn) {	
		super(userID, emailID, Role.CUSTOMER, password, conn); // this will create an entry in persons table
		this.c_address = c_address;
		this.c_tel_no = c_tel_no;
		this.c_name = c_name;
		this.service_center = serviceCen_id;
		createCustomer(conn);
	}
    
    public String getCustomerID() {
    	return this.c_id;
    }
    
    void customerMenu() {
    	Boolean exit = false;
    	while(!exit) {
    		System.out.println("Name: " + this.c_name);
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
    			CarRegister cr = new CarRegister(Role.CUSTOMER, this.con);
			this.vehicleList.add(cr.registerCar(this.c_id));
    		} else if (input.startsWith("3")){
    			this.servicePage();
    		}else if (input.startsWith("4")){
    			System.out.println("Viewing invoices");
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
    	PreparedStatement pstmt = null;
		try{
			pstmt = this.conn.prepareStatement("INSERT INTO CUSTOMER "
					+ "(C_ID, C_EMAIL, C_ADDR, C_TEL_NO, C_NAME, SC_ID) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?)");			
			pstmt.setString(1, this.emailID);
			pstmt.setString(2, this.emailID);
			pstmt.setString(3, this.c_address);
			pstmt.setString(4, this.c_tel_no);
			pstmt.setString(5, this.c_name);
			pstmt.setString(6, this.service_center);
			pstmt.executeQuery();
			if(pstmt.executeUpdate() == 0)
				System.out.println("Employee Create Failed");
			else
				System.out.println("Employee created successfully");
		}catch(SQLException e){
//			e.printStackTrace();
		}	
    }
    private void profilePage() {
    	boolean exit = false;
		while(!exit) {
			System.out.println("1.  View Profile");
			System.out.println("2.  Update Profile");
			System.out.println("3.  Go Back");
			String input = reader.nextLine();
			if (input.startsWith("1")) {
				displayProfile();
    		}
			else if (input.startsWith("2")) {
    			updateProfile();
    		}
			else if (input.startsWith("3")) {
    			exit = true;
    		}
		}
    }
    public void displayProfile() {
    		System.out.println("Profile");
    		System.out.println("Customer ID: " + this.c_id);
    		System.out.println("Name: " + this.c_name);
    		System.out.println("Address: " + this.c_address);
    		System.out.println("Email Address: " + this.emailID);
    		System.out.println("Phone Number: " + this.c_tel_no);
    		System.out.println("Cars: ");
    		for(int i=0; i<this.vehicleList.size(); i++) {
    			System.out.print(i+1);
    			Vehicle tmp = vehicleList.get(i);
    			tmp.vehicleProfile();
    			
    		}
    		boolean exit = false;
    		while(!exit) {
    			System.out.println("1.  Go Back");
    			String input = reader.nextLine();
    			if (input.startsWith("1")) {
        			exit = true;
        		}
    		}
    		
    }
   
//    private void registerCarPage() {
//    	System.out.println("Register Car");
//    	
//    	
//    }
    private void servicePage() {
    		System.out.println("Start a service");
    		ServicePage sp = new ServicePage(Role.CUSTOMER, this.conn);
    		sp.customerServicePage(this);
    }
    
    private void invoicePage() {
    		invoices = new Vector<InvoicePage>(); 
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		srID = new ArrayList<String>();
    		try{
    			String query = "SELECT service.ser_id "
    					+ "FROM service "
    					+ "WHERE service.c_id = ?";
    			pstmt = con.prepareStatement(query);
    			pstmt.setString(1, this.c_id);
    			rs = pstmt.executeQuery();
    			while(rs.next())  {
    				srID.add(rs.getString(1));
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		for(int i = 0; i < srID.size();i++) {
    			System.out.println("INVOICE NO " + (i+1));
    			invoices.add(new InvoicePage(this.con, srID.get(i)));
    			invoices.get(i).printInvoices();
//    			System.out.println(srID.get(i) + invoices.get(i).mechanicName + invoices.get(i).make);
    		}
    		boolean exit = false;
    		while(!exit) {
    			System.out.println("\n1. View Invoice Details");
    			System.out.println("2. Go Back");
    			String input = reader.nextLine();
    			if (input.startsWith("1")) {
    				System.out.println("### View Invoice Details ###");
    				System.out.println("Enter Service ID: ");
    				input = reader.nextLine();
    				invoices.get(srID.indexOf(input)).printInvoicesDetailed();
    			}
    			else if (input.startsWith("2")) {
    				exit = true;
    			}
    		}
    		
    }
    
    private void updateProfile() {
    	boolean exit = false;
		while(!exit) {
			System.out.println("1. Name");
			System.out.println("2. Address");
			System.out.println("3. Phone Number");
			System.out.println("4. Password");
			System.out.println("5. Go Back");
			String input = reader.nextLine();
			if (input.startsWith("1")) {
				System.out.println("Enter New Name");
				input = reader.nextLine();
				if(updateCustInfo(1, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("2")) {
				System.out.println("Enter New Address");
				input = reader.nextLine();
				if(updateCustInfo(2, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("3")) {
				System.out.println("Enter New Phone Number");
				input = reader.nextLine();
				if(updateCustInfo(3, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("4")) {
				System.out.println("Enter New Password");
				input = reader.nextLine();
				if(updateCustInfo(4, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("5")) {
    			exit = true;
    		}
		}
    }
    void deleteCustomer(Connection conn) {
    		
		personDelete();
    }
    
    //TODO to be optimized
    public boolean updateCustInfo(int index, String val){
    	PreparedStatement pstmt = null;
		try{ 	
			if(index == 4) {
				 pstmt = con.prepareStatement("UPDATE Person SET PASSWORD = ? WHERE C_ID = ?");
					pstmt.setString(1, val);
					pstmt.setString(2, this.c_id);
					pstmt.executeQuery();
					if(pstmt.executeUpdate() == 0){
						// Failure
						return false;
					}
					this.password = val;
			}
			else if(index == 1) {

				 pstmt = con.prepareStatement("UPDATE Customer SET C_NAME = ? WHERE C_ID = ?");
					pstmt.setString(1, val);
					pstmt.setString(2, this.c_id);
					pstmt.executeQuery();
					if(pstmt.executeUpdate() == 0){
						// Failure
						return false;
					}
					this.c_name = val;
			}
			else if(index == 2) {
				 pstmt = con.prepareStatement("UPDATE Customer SET C_ADDR = ? WHERE C_ID = ?");
					pstmt.setString(1, val);
					pstmt.setString(2, this.c_id);
					pstmt.executeQuery();
					if(pstmt.executeUpdate() == 0){
						// Failure
						return false;
					}
					this.c_address = val;
			}
			else if(index == 3) {
				pstmt = con.prepareStatement("UPDATE Customer SET C_TEL_NO = ? WHERE C_ID = ?");
				 pstmt.setString(1, val);
				 pstmt.setString(2, this.c_id);
				 pstmt.executeQuery();
					if(pstmt.executeUpdate() == 0){
						// Failure
						return false;
					}
					this.c_tel_no = val;
			}
			
		}catch(SQLException e){
			return false;
		}
		return true;
	}

}