package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
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
	Connection conn;
	static final int withid = 1;
	static final int withemail = 2;
	
	Employee(Person p, Connection conn){
		super(p);
		this.conn = conn;
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
	
	Employee(Employee emp, Connection conn){
		super(conn);
		
	}
	
	// flag true to create using emp_id , false to create using email
	//this object constructor should fetch from database
	Employee(String id, Connection conn, int flag){
		super(conn);
		if (withid == flag) {
			//////TODO
			// Q1 select using employee id from Employee table and update instance variables.
			// Q2 fetch from persons table and set Persons instance variable 
			
		} else if (withemail == flag){
			 /////TODO
			// Q1 select using employee email from Employee table and update instance variables.
			// Q2 fetch from persons table and set Persons instance variable 			
		}
	}
	
	
    Employee(String userID, String emailID, String name, String password, int my_role, String sc_id,
    		String e_address, String e_tel_no, Date start_date, int compensation, Connection conn) {	
		super(userID, emailID, my_role, password, conn); // this will create an entry in persons table
		this.conn = conn;
		this.e_name = name;
		this.service_center = sc_id;
		this.e_address = e_address;
		this.e_tel_no = e_tel_no;
		this.start_date = start_date;
		this.compensation = compensation;
		
		createEmployee();
	}

    
    void createEmployee() {
    	
    	String temp = Role.getRole(this.my_role);
		
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = this.conn.prepareStatement("INSERT INTO EMPLOYEE "
					+ "(E_ID, E_NAME, E_EMAIL, SC_ID, E_ADDRESS, E_TEL_NO, E_ROLE, START_DATE, COMPENSATION) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, this.emailID);
			pstmt.setString(2, this.e_name);
			pstmt.setString(3, this.emailID);
			pstmt.setString(4, this.service_center);
			pstmt.setString(5, this.e_address);
			pstmt.setString(6, this.e_tel_no);
			pstmt.setString(7, temp);
			pstmt.setDate(8, this.start_date);
			pstmt.setInt(9, this.compensation);
			pstmt.executeQuery();
			if(pstmt.executeUpdate() == 0)
				System.out.println("Employee Create Failed");
			else
				System.out.println("Employee created successfully");
		}catch(SQLException e){
//			e.printStackTrace();
		}	
    }
 
    void deleteEmployee(Connection conn) {
    		//TODO
    		personDelete();
    }
    
    static boolean  employeeExists(String emp_id) {
    		//TODO
    		//Check if employee id exists in table
    	 return true;
    }

	public void displayPayroll() {
		// TODO Auto-generated method stub
		
	}

    
}



class Manager extends Employee implements MonthlyPayable{
	
	Scanner reader = new Scanner(System.in);; 
	Connection conn;
	
	Manager(String ID, Connection conn, int flag){
		super(ID, conn, flag);
	}
	
	// used during login
	Manager(Person p, Connection conn){
		super(p, conn);
		this.conn = conn;
		
	}
	
//	Manager(int emp_id, Connection conn){
//		super(emp_id, conn);
//	}
	
	
	Manager(String userID, String emailID,String name, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		super(userID, emailID, name, password, Role.MANAGER, sc_id,
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
    			this.addEmployee();
    		} else if (input.startsWith("4")){
    			continue;
    		} else if (input.startsWith("5")){
    			this.displayInventory();
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

		System.out.println("Enter Customer Email ID");
		String input = reader.nextLine();
		this.displayCustomerProfile(input);
		
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
		
		boolean exit = false;
		while(!exit) {
			System.out.println("1.  Go Back");
			String input = reader.nextLine();
			if (input.startsWith("1")) {
    			exit = true;
    		}
		}
    }
    

    private void addEmployee() {
    	boolean goback = false;
    		
    	while (!goback){
    		System.out.println(" Enter  Name");
    		String name = reader.nextLine();
    		System.out.println(" Enter  Address");
    		String address= reader.nextLine();
    		System.out.println(" Enter  EmailAddress");
    		String emailad = reader.nextLine();
    		System.out.println("Enter  PhoneNumber");
    		String phoneno = reader.nextLine();
    		System.out.println("Enter  Role");
    		String emprole = reader.nextLine();
    		System.out.println("Enter  Start Date");
    		String startdate = reader.nextLine();
    		Date date1;
    		java.util.Date date;
    		java.sql.Date sqlStartDate = null;
    		try {
    			SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
    			date = sdf1.parse(startdate);
    			sqlStartDate = new java.sql.Date(date.getTime()); 
    			
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		System.out.println("Enter  Compensation");
    		String compesation = reader.nextLine();
    		System.out.println("Enter  1 to add 0 to go back");
    		String input = reader.nextLine();
    			
    			if (input.startsWith("1")) {
    			
    				if (emprole.toLowerCase().equals("receptionist")) {
    					// if ServiceCenter has more than one receptionist.
    					
    					ServiceCenter sc = new ServiceCenter(this.service_center);
    					if (!sc.hasReceptionist(conn))
    					{
    					
    						//String userID,String emailID, String password, String sc_id,
    				   		// String e_address, String e_tel_no, Date start_date, int compensation, Connection conn
    						Receptionist newRecp = new Receptionist(emailad, emailad , name, "12345678", this.service_center,
    								address, phoneno, sqlStartDate, Integer.parseInt(compesation), conn);
    					    break;
    					} else {
    						System.out.println("Wrong role. hit 0 to go back or hit 1 to enter again");
    						input = reader.nextLine();
    						
    						if(input.startsWith("0")) {
    						
    							//goback = true;
    							break;
    						} else if(input.startsWith("1")) {
    							continue;
    						}
    					}
    					
    				} else if (emprole.toLowerCase().equals("mechanic")) {
    					
    					Mechanic newMech = new Mechanic(emailad, emailad , name,  "12345678", this.service_center,
    							address, phoneno, sqlStartDate, Integer.parseInt(compesation), conn);	
    					break;
    				} else {
    					System.out.println("Wrong role. hit 0 to go back or hit 1 to enter again");
    					input = reader.nextLine();
    					
    					if(input.startsWith("0")) {
    					
    						//goback = true;
    						break;
    					} else if(input.startsWith("1")) {
    						continue;
    					}
    				}
    			} else if (input.startsWith("0")) {
    				return;
    			}
    		}
    	}
    	
    	
    	private void payrollPage() {
    		String input;
    		boolean exists = false;
    		
    		do {
    			System.out.println("Enter Employee id");
    			input = reader.nextLine();
    			if (Employee.employeeExists(input)) {	
    			    Employee emp = new Employee(input, conn, Employee.withid);
    			    if(emp.my_role == Role.RECEPTIONIST) {
    			    	Receptionist recEmp = new  Receptionist(emp, conn);
    			    	
    			    	 	displayPayroll();
    			    } else if (emp.my_role == Role.MECHANIC) {
    			    		emp.displayPayroll();
    			    } else if (emp.my_role == Role.MANAGER) {
    			    	
    			    }
    			   
    			    exists =true;
    			} else {
    				System.out.println("Employee id no found hit 1 to enter again, 0 to go back");
    				input = reader.nextLine();
    				if(input.startsWith("1")) {
    					continue;
    				} else {
    					exists = true;
    				}
    			}	
    		} while (!exists);	
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

	 
	private void servicePage() {
		
	}
	
	private void invoicePage() {
		
	}
	
}

class Receptionist extends Employee implements MonthlyPayable{
	
	Receptionist(String ID, Connection conn, int flag){
		super(ID, conn, flag);
	}
	
	Receptionist(Person p, Connection conn){
		super(p, conn);
	}
	
	
	Receptionist(String userID, String emailID, String name, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		super(userID, emailID, name, password, Role.RECEPTIONIST, sc_id,
		   		e_address, e_tel_no, start_date, compensation, conn);			
	}
	
	
	
	public Receptionist(Employee emp, Connection conn) {
		super(emp, conn);
		// TODO Auto-generated constructor stub
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
	@Override
	public void displayPayroll() {
		// TODO Auto-generated method stub
		
	}
	
}

class Mechanic extends Employee implements HourlyPayable{
	Mechanic(String ID, Connection conn, int flag){
		super(ID, conn, flag);
	}
	
	Mechanic(Person p, Connection conn){
		super(p, conn);
	}
	
	
	Mechanic(String userID, String emailID, String name, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		super(userID, emailID, name, password, Role.MECHANIC, sc_id,
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

	@Override
	public void displayPayroll() {
		// TODO Auto-generated method stub
		
	}
}
