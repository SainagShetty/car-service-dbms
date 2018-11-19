package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class Employee extends Person {
	String eid;
	String e_name;
	String service_center; 
	String e_address;
    String  e_tel_no;
	Date start_date;
	int compensation;
	
	Employee(Person p){
		super(p);
	}
	
	Employee(int emp_id, Connection conn){
		super(conn); //just updates connection 
		// Q1 fetch from Employee table and update instance variables.
		// Q2 fetch from persons table and set Persons instance variable 
		
	}
	//this object constructor should fetch from database
	Employee(String emailID, Connection conn ){
		super(emailID, conn);
	}

    Employee(String userID,String emailID, String password, int my_role, String sc_id,
    		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn) {	
		super(emailID, my_role, conn); // this will create an entry in persons table
		service_center = sc_id;
		this.e_address = e_address;
		this.compensation = compensation;
		this.start_date = start_date;
		this.e_tel_no = e_tel_no;
		createEmployee(conn);
	}
    
    void createEmployee(Connection conn) {
    		// create entry in Employee table.
    }
    
    void deleteEmployee(Connection conn) {
    	
    		personDelete();
    }
    

}



class Manager extends Employee implements MonthlyPayable{
	
	Scanner reader; 
	Connection conn;
	
	Manager(String emailID, Connection conn){
		super(emailID, conn);
	}
	
	// used during login
	Manager(Person p, Connection conn){
		super(p);
		this.conn = conn;
		reader = new Scanner(System.in);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoggedIn = true;
		try{
			pstmt = conn.prepareStatement("SELECT e_id, e_name, sc_id, e_address, e_tel_no, start_date, compensation FROM Employee WHERE e_email=?");
			pstmt.setString(1, p.emailID);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.eid = rs.getString(1);
				this.e_name = rs.getString(2); 
				this.service_center = rs.getString(3); 
				this.e_address = rs.getString(4);
				this.e_tel_no = rs.getString(5);
				this.start_date = rs.getDate(6);
				this.compensation = rs.getInt(7);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	Manager(int emp_id, Connection conn){
		super(emp_id, conn);
	}
	
	Manager(String userID,String emailID, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		super(userID, emailID,password, Role.MANAGER, sc_id,
		   		e_address, e_tel_no, start_date, compensation, conn);	
	}
	
	void managerMenu() {
		System.out.println(this.e_name);
		Boolean exit = false;
    	while(!exit) {
    		System.out.println("Name: " + this.e_name);
        	System.out.println("### Manager landing page ###");
    		System.out.println("1.  Profile");
    		System.out.println("2.  View Customer Profile");
    		System.out.println("3.  Add New Employees");
    		System.out.println("4.  Payroll");
    		System.out.println("5.  Inventory");
    		System.out.println("6.  Orders");
    		System.out.println("7.  Notifications");
    		System.out.println("8.  New Car Model");
    		System.out.println("9.  Car Service Details");
    		System.out.println("10.  Service History");
    		System.out.println("11.  Invoices");
    		System.out.println("12.  Logout");
    		
    		String input = reader.nextLine();
    		if (input.startsWith("12")) {
    			signout();
    			exit = true;
    		} else if (input.startsWith("11")){
    			continue;
    		} else if (input.startsWith("10")){
    			continue;
    		} else if (input.startsWith("1")){
    			this.profilePage();
    		} else if (input.startsWith("2")) {
    			this.customerProfile();
    		} else if (input.startsWith("3")){
    			continue;
    		} else if (input.startsWith("4")){
    			continue;
    		} else if (input.startsWith("5")){
    			continue;
    		} else if (input.startsWith("6")){
    			continue;
    		} else if (input.startsWith("7")){
    			continue;
    		} else if (input.startsWith("8")){
    			continue;
    		} else if (input.startsWith("9")){
    			continue;
    		} else {
    			exit = true;
    		}
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
				this.displayProfile();
    		}
			else if (input.startsWith("2")) {
    			this.updateProfile();
    		}
			else if (input.startsWith("3")) {
    			exit = true;
    		}
		}
    }
	
	private void displayProfile() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		System.out.println("Profile");
		System.out.println("Employee ID: " + this.eid);
		System.out.println("Name: " + this.e_name);
		System.out.println("Address: " + this.e_address);
		System.out.println("Email Address: " + this.emailID);
		System.out.println("Phone Number: " + this.e_tel_no);
		//Display Service Center Name?
		System.out.println("Service Center: "+this.service_center);
		//Update from table
		System.out.println("Role: Manager");
		System.out.println("Date: "+formatter.format(start_date));
		System.out.println("Compensation: "+Integer.toString(compensation));
		//Update from table
		System.out.println("Compensation Frequency: Monthly");

		
		boolean exit = false;
		while(!exit) {
			System.out.println("1.  Go Back");
			String input = reader.nextLine();
			if (input.startsWith("1")) {
    			exit = true;
    		}
		}
		
	}
	
	private void updateProfile() {
    	boolean exit = false;
		while(!exit) {
			System.out.println("1. Name");
			System.out.println("2. Address");
			System.out.println("3. Email Address");
			System.out.println("4. Phone Number");
			System.out.println("5. Password");
			System.out.println("6. Go Back");
			String input = reader.nextLine();
			if (input.startsWith("1")) {
				System.out.println("Enter New Name");
				input = reader.nextLine();
				if(updateEmpInfo(1, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("2")) {
				System.out.println("Enter New Address");
				input = reader.nextLine();
				if(updateEmpInfo(2, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("3")) {
				System.out.println("Enter Email Address");
				input = reader.nextLine();
				if(updateEmpInfo(3, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("4")) {
				System.out.println("Enter New Phone Number");
				input = reader.nextLine();
				if(updateEmpInfo(4, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("5")) {
				System.out.println("Enter New Password");
				input = reader.nextLine();
				if(updateEmpInfo(5, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("6")) {
    			exit = true;
    		}
		}
    }
	
	//TODO to be optimized
    public boolean updateEmpInfo(int index, String val){
    	PreparedStatement pstmt = null;
		if(index == 5) {
			try
			{
				pstmt = conn.prepareStatement("UPDATE PERSON SET PASSWORD = ? WHERE USERID = ?");
				pstmt.setString(1, val);
				pstmt.setString(2, this.eid);
				pstmt.executeQuery();
				if(pstmt.executeUpdate() == 0){
					// Failure
					return false;
				}
				this.password = val;
				return true;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		else if(index == 1) {
			
			try {
				pstmt = conn.prepareStatement("UPDATE EMPLOYEE SET E_NAME = ? WHERE E_ID = ?");
				pstmt.setString(1, val);
				pstmt.setString(2, this.eid);
				pstmt.executeQuery();
				if(pstmt.executeUpdate() == 0){
					// Failure
					return false;
				}
				this.e_name = val;
				System.out.println("Updating name done");
				return true;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		else if(index == 2) {
			try {
				pstmt = conn.prepareStatement("UPDATE Employee SET E_ADDRESS = ? WHERE E_ID = ?");
				pstmt.setString(1, val);
				pstmt.setString(2, this.eid);
				pstmt.executeQuery();
				if(pstmt.executeUpdate() == 0){
					// Failure
					return false;
				}
				this.e_address = val;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		else if(index == 3) {
			try {
				//Create a trigger to update Email address in person table (EMAILID) from employee table (E_EMAIL)
				pstmt = conn.prepareStatement("UPDATE Employee SET E_EMAIL = ? WHERE E_ID = ?");
				pstmt.setString(1, val);
				pstmt.setString(2, this.eid);
				pstmt.executeQuery();
				if(pstmt.executeUpdate() == 0){
					// Failure
					return false;
				}
				this.emailID = val;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		else if(index == 4) {
			try {
				pstmt = conn.prepareStatement("UPDATE Employee SET E_TEL_NO = ? WHERE E_ID = ?");
				pstmt.setString(1, val);
				pstmt.setString(2, this.eid);
				pstmt.executeQuery();
				if(pstmt.executeUpdate() == 0){
					// Failure
					return false;
				}
				this.e_tel_no = val;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return true;
	}
    
    private void customerProfile() {
    	boolean exit = false;
		while(!exit) {
			System.out.println("Enter Customer Email ID");
			String input = reader.nextLine();
			this.displayCustomerProfile(input);
			System.out.println("1. Go Back");
			input = reader.nextLine();
			if (input.startsWith("1")) {
				exit = true;
    		}
		}
    }
    
    public void displayCustomerProfile(String emailID) {
    	boolean status = false;
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT c_id, c_name, sc_id, c_addr, c_tel_no FROM Customer WHERE c_email=?");
			pstmt.setString(1, emailID);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				
				System.out.println("Customer Profile");
				String c_id = rs.getString(1);
	    		System.out.println("Customer ID: " + c_id);
	    		System.out.println("Name: " + rs.getString(2));
	    		System.out.println("Address: " + rs.getString(4));
	    		System.out.println("Email Address: " + emailID);
	    		System.out.println("Phone Number: " + rs.getString(5));
	    		System.out.println("Cars: ");
	    		
	    		Vector<Vehicle> vehicleList = new Vector<Vehicle>();
	    		PreparedStatement pstmt2 = null;
	    		ResultSet rs2 = null;
	    		try{
	    			pstmt2 = conn.prepareStatement("SELECT license_no FROM Vehicle WHERE c_id=?");
	    			pstmt2.setString(1, c_id);
	    			rs2 = pstmt2.executeQuery();
	    			while(rs2.next())  {
	    				String tmp = rs2.getString(1);
	    				Vehicle v = new Vehicle(tmp);
	    				vehicleList.add(v);
//	    				System.out.println(tmp);
	    			}
	    		}catch(SQLException e){
	    			e.printStackTrace();
	    		}
	    		
	    		for(int i=0; i<vehicleList.size(); i++) {
	    			System.out.print(i+1);
	    			Vehicle tmp = vehicleList.get(i);
	    			tmp.vehicleProfile();	
	    		}
	    		status = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(!status)
		{
			System.out.println("Enter Email Id is not assigned to any customer");
		}
    }

	@Override
	public String lastPaymenDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update_no_days_worked(int days) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_no_of_days_worked() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateMonthlyPayment() {
		// TODO Auto-generated method stub
		return 0;
	}

}


class Receptionist extends Employee implements MonthlyPayable{
	
	Receptionist(String emailID, Connection conn){
		super(emailID, conn);
	}
	Receptionist(Person p, Connection conn){
		super(p);
		//get other details from database
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoggedIn = false;
		try{
			pstmt = conn.prepareStatement("SELECT e_id, e_name, sc_id, e_address, e_tel_no, start_date, compensation FROM Employee WHERE e_email=?");
			pstmt.setString(1, p.emailID);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.eid = rs.getString(1);
				this.e_name = rs.getString(2); 
				this.service_center = rs.getString(3); 
				this.e_address = rs.getString(4);
				this.e_tel_no = rs.getString(5);
				this.start_date = rs.getDate(6);
				this.compensation = rs.getInt(7);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	Receptionist(int emp_id, Connection conn){
		super(emp_id, conn);
	}
	
	Receptionist(String userID,String emailID, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		super(userID, emailID,password, Role.RECEPTIONIST, sc_id,
		   		e_address, e_tel_no, start_date, compensation, conn);	
	}

	
	@Override
	public String lastPaymenDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update_no_days_worked(int days) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_no_of_days_worked() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateMonthlyPayment() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	void ReceptionistMenu() {
		
		
		
	}
	
}

class Mechanic extends Employee implements HourlyPayable{
	Mechanic(String emailID, Connection conn){
		super(emailID, conn);
	}
	
	Mechanic(Person p, Connection conn){
		super(p);
		//get other details from database
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoggedIn = false;
		try{
			pstmt = conn.prepareStatement("SELECT e_id, e_name, sc_id, e_address, e_tel_no, start_date, compensation FROM Employee WHERE e_email=?");
			pstmt.setString(1, p.emailID);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.eid = rs.getString(1);
				this.e_name = rs.getString(2); 
				this.service_center = rs.getString(3); 
				this.e_address = rs.getString(4);
				this.e_tel_no = rs.getString(5);
				this.start_date = rs.getDate(6);
				this.compensation = rs.getInt(7);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	Mechanic(int emp_id, Connection conn){
		super(emp_id, conn);
	}
	
	Mechanic(String userID,String emailID, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		super(userID, emailID,password, Role.MECHANIC, sc_id,
		   		e_address, e_tel_no, start_date, compensation, conn);	
	}

	
	@Override
	public String lastPaymenDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update_no_days_worked(int days) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_no_of_days_worked() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateHourlyPayment() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	void MechanicMenu() {
		
		
		
	}
}
