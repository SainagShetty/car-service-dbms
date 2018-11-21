package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
		// TODO employeee details to be copied. 
		service_center = emp.service_center;
		this.e_address = emp.e_address;
		this.e_tel_no = emp.e_tel_no;
		this.start_date =emp.start_date;
		this.compensation = emp.compensation;	
	}
	
	// flag true to create using emp_id , false to create using email
	//this object constructor should fetch from database
	Employee(String id, Connection conn, int flag){
		super(conn);
		if (withid == flag) {
			String roleTmp = "";
			this.conn = conn;
			this.eid = id;
			this.userID = id;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			LoggedIn = true;
			try{
				pstmt = conn.prepareStatement("SELECT e_email, e_name, sc_id, e_address, e_tel_no, start_date, compensation, E_ROLE FROM Employee WHERE e_id=?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next())  {
					this.emailID = rs.getString(1);
					this.e_name = rs.getString(2); 
					this.service_center = rs.getString(3); 
					this.e_address = rs.getString(4);
					this.e_tel_no = rs.getString(5);
					this.start_date = rs.getDate(6);
					this.compensation = rs.getInt(7);
					roleTmp = rs.getString(8);
				}
				if(roleTmp.equals("Manager")) {
					this.my_role = Role.MANAGER;
				}
				else if(roleTmp.equals("Receptionist")) {
					this.my_role = Role.RECEPTIONIST;
				}
				else if(roleTmp.equals("Mechanic")) {
					this.my_role = Role.MECHANIC;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			// Q1 select using employee id from Employee table and update instance variables.
			// Q2 fetch from persons table and set Persons instance variable 
			
		} else if (withemail == flag){
			 /////TODO
			this.conn = conn;
			this.emailID = id;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			LoggedIn = true;
			try{
				pstmt = conn.prepareStatement("SELECT e_id, e_name, sc_id, e_address, e_tel_no, start_date, compensation FROM Employee WHERE e_email=?");
				pstmt.setString(1, id);
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
			this.userID = this.eid;
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
    
    static boolean  employeeExists(String emp_id, Connection con) {
    		//TODO
    		//Check if employee id exists in table
    	PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try{
			pstmt2 = con.prepareStatement("SELECT count(*) FROM Employee WHERE e_id=?");
			pstmt2.setString(1, emp_id);
			rs2 = pstmt2.executeQuery();
			int val = 0;
			while(rs2.next())  {
				val = rs2.getInt(1);
			}
			if(val == 1) {
				return true;
			}
			else {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
    	 return false;
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

	Manager(Employee emp, Connection conn){
		super(emp, conn);
	}
	
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
    		
    		String input = reader.nextLine().trim();
    		if (input.startsWith("12")) {
    			signout();
    			exit = true;
    		} 
    		else if (input.startsWith("14")){
//	    		System.out.println("Enter Customer Email id");
//				String temp_email = reader.nextLine();
//				System.out.println("License Plate");
//				String temp_license = reader.nextLine();
//				System.out.println("Current Milage");
//				float temp_milage = Float.parseFloat(reader.nextLine());
//				System.out.println("Mechanic Name");
//				String temp_ename = reader.nextLine();
//				Maintenance maintenance = new Maintenance(this.service_center, temp_email, temp_license, temp_milage, temp_ename, this.conn);
    		}else if (input.startsWith("11")){
    			this.invoicePage();
    		} else if (input.startsWith("10")){
    			Service.serviceHistory(conn, this.service_center);
    			
    		} else if (input.startsWith("1")){
    			this.profilePage();
    		} else if (input.startsWith("2")) {
    			this.customerProfile();
    		} else if (input.startsWith("3")){
    			this.addEmployee();
    		} else if (input.startsWith("4")){
    			this.payrollPage();
    		} else if (input.startsWith("5")){
    			Inventory inv = new Inventory(this.conn);
    			inv.viewInventory(this.service_center);
    			boolean exit_in = false;
    			while(!exit_in) {
    				System.out.println("1.  Go Back");
    				String input_in = reader.nextLine().trim();
    				if (input_in.startsWith("1")) {
    	    			exit_in = true;
    	    		}
    			}
    		} else if (input.startsWith("6")){
    			ordersPage();
    		} else if (input.startsWith("7")){
    			notificationsPage(this.conn);
    		} else if (input.startsWith("8")){
    			newCarModelPage();
    		} else if (input.startsWith("9")){
    			carServiceDetailsPage();
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
			String input = reader.nextLine().trim();
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
			String input = reader.nextLine().trim();
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
			String input = reader.nextLine().trim();
			if (input.startsWith("1")) {
				System.out.println("Enter New Name");
				input = reader.nextLine().trim();
				if(updateEmpInfo(1, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("2")) {
				System.out.println("Enter New Address");
				input = reader.nextLine().trim();
				if(updateEmpInfo(2, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("3")) {
				System.out.println("Enter Email Address");
				input = reader.nextLine().trim();
				if(updateEmpInfo(3, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("4")) {
				System.out.println("Enter New Phone Number");
				input = reader.nextLine().trim();
				if(updateEmpInfo(4, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("5")) {
				System.out.println("Enter New Password");
				input = reader.nextLine().trim();
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
		String input = reader.nextLine().trim();
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
			String input = reader.nextLine().trim();
			if (input.startsWith("1")) {
    			exit = true;
    		}
		}
    }
    

    private void addEmployee() {
    	boolean goback = false;
    		
    	while (!goback){
    		System.out.println(" Enter  Name");
    		String name = reader.nextLine().trim();
    		System.out.println(" Enter  Address");
    		String address= reader.nextLine().trim();
    		System.out.println(" Enter  EmailAddress");
    		String emailad = reader.nextLine().trim();
    		System.out.println("Enter  PhoneNumber");
    		String phoneno = reader.nextLine().trim();
    		System.out.println("Enter  Role");
    		String emprole = reader.nextLine().trim();
    		System.out.println("Enter  Start Date");
    		String startdate = reader.nextLine().trim();
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
    		String compesation = reader.nextLine().trim();
    		System.out.println("Enter  1 to add 0 to go back");
    		String input = reader.nextLine().trim();
    			
    			if (input.startsWith("1")) {
    			
    				if (emprole.toLowerCase().equals("receptionist")) {
    					// if ServiceCenter has more than one receptionist.
    					
    					ServiceCenter sc = new ServiceCenter(this.service_center, this.conn);
    					if (!sc.hasReceptionist(conn))
    					{
    					
    						//String userID,String emailID, String password, String sc_id,
    				   		// String e_address, String e_tel_no, Date start_date, int compensation, Connection conn
    						Receptionist newRecp = new Receptionist(emailad, emailad , name, "12345678", this.service_center,
    								address, phoneno, sqlStartDate, Integer.parseInt(compesation), conn);
    					    break;
    					} else {
    						System.out.println("Wrong role. hit 0 to go back or hit 1 to enter again");
    						input = reader.nextLine().trim();
    						
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
    					input = reader.nextLine().trim();
    					
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
    			input = reader.nextLine().trim();
    			if (Employee.employeeExists(input, conn)) {	
    				
//    			    Employee emp = new Employee(input, conn, Employee.withid);
//    			    System.out.println(emp.e_name + " " + emp.my_role);
//    			    if(emp.my_role == Role.RECEPTIONIST) {
//    			    	System.out.println("R");
//    			    	Receptionist recEmp = new  Receptionist(emp, conn);
//    			    		recEmp.displayPayroll();	
//    			    } else if (emp.my_role == Role.MECHANIC) {
//    			    	System.out.println("M");
//    			      	Mechanic mecEmp = new  Mechanic(emp, conn);
//    			    		mecEmp.displayPayroll();
//    			    } else if (emp.my_role == Role.MANAGER) {
//    			    	System.out.println("Mng");
//    			    		Manager manEmp = new  Manager(emp, conn);
//    			    		manEmp.displayPayroll();
//    			    }
//    			   
    				viewPayroll(input);
    			    exists =true;
    			} else {
    				System.out.println("Employee id no found hit 1 to enter again, 0 to go back");
    				input = reader.nextLine().trim();
    				if(input.startsWith("1")) {
    					continue;
    				} else {
    					exists = true;
    				}
    			}	
    		} while (!exists);	
    	}
    	
    private void viewPayroll(String id) {
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoggedIn = true;
		try{
			pstmt = conn.prepareStatement("SELECT Payroll.LASTPAYCHECK, "
					+ "Payroll.LASTPAYCHECK-15, "
					+ "Payroll.e_id, "
					+ "Employee.e_name, "
					+ "Employee.compensation, "
					+ "Payroll.E_ROLE, "
					+ "Payroll.EARNINGSYTD, "
					+ "Payroll.PREVEARNINGS, "
					+ "CASE employee.e_role when 'Manager' "
					+ "then ((payroll.prevearnings/employee.compensation)*30) when 'Receptionist' "
					+ "then ((payroll.prevearnings/employee.compensation)*30) when 'Mechanic' "
					+ "then ROUND(((payroll.prevearnings/employee.compensation)), 1) END as unit, "
					+ "CASE employee.e_role when 'Manager' then 'Monthly' when 'Receptionist' "
					+ "then 'Monthly' when 'Mechanic' then 'Hourly' END as frequency FROM Payroll, "
					+ "Employee WHERE Employee.e_id = Payroll.e_id and Payroll.e_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				Date checkDate = rs.getDate(1);
				System.out.println("A. Paycheckdate: " + checkDate);
				System.out.println("B. Payperiod: " + rs.getDate(2) + " - " + checkDate);
				System.out.println("C. EmployeeID: " + rs.getString(3));
				System.out.println("D. Employee Name: " + rs.getString(4));
				System.out.println("E. Compensation($): " + rs.getInt(5));
				System.out.println("F. Compensation Frequency: " + rs.getString(10));
				System.out.println("G. Units: " + rs.getFloat(9));
				System.out.println("H. Earnings (Current): " + rs.getFloat(8));
				System.out.println("I. Earnings (Year-to-date): " + rs.getFloat(7) );
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		boolean exit = false;
		while(!exit) {
			System.out.println("\n1. Go Back");
			String input = reader.nextLine();
			if(input.startsWith("1")) {
				exit = true;
			}
		}
    }
    	
    private void displayInventory() {
    	
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
	
	private void invoicePage() {
		System.out.println("Viewing Invoices: ");
		Vector<InvoicePage> invoices = new Vector<InvoicePage>(); 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> srID = new ArrayList<String>();
		try{
			String query = "SELECT service.ser_id "
					+ "FROM service "
					+ "WHERE service.SC_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, this.service_center);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				srID.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i = 0; i < srID.size();i++) {
			System.out.println("INVOICE NO " + (i+1));
			invoices.add(new InvoicePage(this.conn, srID.get(i)));
			invoices.get(i).printInvoices();
//			System.out.println(srID.get(i) + invoices.get(i).mechanicName + invoices.get(i).make);
		}
		boolean exit = false;
		while(!exit) {
			System.out.println("\n1. Go Back");
			String input = reader.nextLine();
			if(input.startsWith("1")) {
				exit = true;
			}
		}
	}

	private void ordersPage() {
		boolean goback = false;
		while(!goback) {
			System.out.println("### Orders ###");
			System.out.println("Select from below");
			System.out.println("1. Order History");
			System.out.println("2. New Order");
			System.out.println("3. Go Back");
			String input = reader.nextLine().trim();
			if( input.startsWith("1")) {
				System.out.println("###Order History###");
				
				Order.printOrderHistory(conn);
				
				boolean exit_in = false;
				while(!exit_in) {
					System.out.println("1.  Go Back");
					String input_in = reader.nextLine().trim();
					if (input_in.startsWith("1")) {
		    			exit_in = true;
		    			}
				}
				
			} else if (input.startsWith("2")) {
				System.out.println("###New Order###");
				System.out.println("Enter Part ID");
				String req_part = reader.nextLine().trim();
				System.out.println("Enter Quantity");
				String req_quantity = reader.nextLine().trim();
				Order new_order = new Order(this.service_center);
				
				//confirm 
				boolean exit_in = false;
				while (!exit_in) {
					System.out.println("1. Place Order");
					System.out.println("2. Goback");
					input = reader.nextLine().trim();
						
						if( input.startsWith("1")) {
							new_order.placeOrder(req_part, req_quantity, conn);
							exit_in = true;
						} else if (input.startsWith("2")){
							exit_in = true;
						} else {
							System.out.println("Invalid Input. Try Again");
						}
				}
			} else if (input.startsWith("3")) {
				goback = true;
			} else {
				System.out.println("Invalid Input. Try Again");
			}
		}	
	}
	private void notificationsPage(Connection conn) {
		Notification.notificationPage(this.service_center,conn);		
	}
	private void newCarModelPage() {
			
	}
	private void carServiceDetailsPage() {
		
		
	}
	
	
	@Override
	public void displayPayroll() {
		// TODO Auto-generated method stub
		
		
	}
}

class Receptionist extends Employee implements MonthlyPayable{
	Scanner reader =  new Scanner(System.in);
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
	
		boolean exit = false;
		while(!exit) {
    		
	        	System.out.println("### Receptionist landing page ###");
	    		System.out.println("1.  Profile");
	    		System.out.println("2.  View Customer Profile");
	    		System.out.println("3.  Register Car");
	    		System.out.println("4.  Service History");
	    		System.out.println("5.  Schedule Service");
	    		System.out.println("6.  Reschedule\n" + 
	    				"Service");
	    		System.out.println("7.  Invoices");
	    		System.out.println("8.   Daily Task-Update\n" + 
	    				"Inventory");
	    		System.out.println("9.  Daily\n" + 
	    				"Task-Record\n" + 
	    				"Deliveries");
	    		System.out.println("10.  Logout");
	    		
	    		String input = reader.nextLine().trim();
	    		if (input.startsWith("10")) {
	    			signout();
	    			exit = true;
	    		} else if (input.startsWith("1")){
	    			this.profilePage();
	
	    		} else if (input.startsWith("2")){
	    			this.customerProfile();
	    		
	    		} else if ( input.startsWith("3")){
	    			CarRegister cr = new CarRegister(Role.RECEPTIONIST, this.conn);
	    			System.out.println("Enter customer email");
	    			String cus_email = reader.nextLine().trim();
	    			Customer cus = new Customer(cus_email, conn); 
	    			cus.vehicleList.add(cr.registerCar(cus.getCustomerID()));
	    			
	    		} else if ( input.startsWith("4")){
	    			System.out.println("Enter customer email");
	    			String cus_email = reader.nextLine().trim();
	    			Service.serviceHistory(cus_email, conn);
	    			
	    		} else if ( input.startsWith("5")){
	    			ServicePage recp_sp = new ServicePage(Role.RECEPTIONIST, this.conn);
	    			recp_sp.receptionistScheduleServicePage(this.service_center);
	    			
	    		} else if ( input.startsWith("6")){
	    			ServicePage recp_sp = new ServicePage(Role.RECEPTIONIST, this.conn);
	    			recp_sp.receptionistRescheduleServicePage();
	    			
	    		} else if ( input.startsWith("7")){
	    			// Invoices Sainag
	    			this.invoicesDisplay();
	    		} else if ( input.startsWith("8")){
	    			dailyTaskUpdateInventory();
	    			
	    		} else if ( input.startsWith("9")){
	    			dailyTaskRecordDeliveries();
	    			// Daily task Record Deliveries
	    		}
		}
	}
	@Override
	public void displayPayroll() {
		// TODO Auto-generated method stub
		
	}
	
	private void invoicesDisplay() {
		System.out.println("\nEnter Customer email ID: ");
		String custID = "";
		String custEmail = reader.nextLine();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String query = "SELECT C_ID "
					+ "FROM Customer "
					+ "WHERE C_EMAIL = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, custEmail);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				custID = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Vector<InvoicePage> invoices = new Vector<InvoicePage>(); 
		pstmt = null;
		rs = null;
		ArrayList<String> srID = new ArrayList<String>();
		try{
			String query = "SELECT service.ser_id "
					+ "FROM service "
					+ "WHERE service.c_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, custID);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				srID.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i = 0; i < srID.size();i++) {
			System.out.println("INVOICE NO " + (i+1));
			invoices.add(new InvoicePage(conn, srID.get(i)));
			invoices.get(i).printInvoices();
//			System.out.println(srID.get(i) + invoices.get(i).mechanicName + invoices.get(i).make);
		}
		boolean exit = false;
		while(!exit) {
			System.out.println("\n1. Go Back");
			String input = reader.nextLine();
			if (input.startsWith("1")) {
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
			String input = reader.nextLine().trim();
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
	
	private void updateProfile() {
    	boolean exit = false;
		while(!exit) {
			System.out.println("1. Name");
			System.out.println("2. Address");
			System.out.println("3. Email Address");
			System.out.println("4. Phone Number");
			System.out.println("5. Password");
			System.out.println("6. Go Back");
			String input = reader.nextLine().trim();
			if (input.startsWith("1")) {
				System.out.println("Enter New Name");
				input = reader.nextLine().trim();
				if(updateEmpInfo(1, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("2")) {
				System.out.println("Enter New Address");
				input = reader.nextLine().trim();
				if(updateEmpInfo(2, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("3")) {
				System.out.println("Enter Email Address");
				input = reader.nextLine().trim();
				if(updateEmpInfo(3, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("4")) {
				System.out.println("Enter New Phone Number");
				input = reader.nextLine().trim();
				if(updateEmpInfo(4, input)){
					System.out.println("Success");
				}
				else {
					System.out.println("Error");
				}
    		}
			if (input.startsWith("5")) {
				System.out.println("Enter New Password");
				input = reader.nextLine().trim();
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
		System.out.println("Role: Receptionist");
		System.out.println("Date: "+formatter.format(start_date));
		System.out.println("Compensation: "+Integer.toString(compensation));
		//Update from table
		System.out.println("Compensation Frequency: Monthly");

		
		boolean exit = false;
		while(!exit) {
			System.out.println("1.  Go Back");
			String input = reader.nextLine().trim();
			if (input.startsWith("1")) {
    			exit = true;
    		}
		}
		
	}
	
    private void customerProfile() {

		System.out.println("Enter Customer Email ID");
		String input = reader.nextLine().trim();
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
			String input = reader.nextLine().trim();
			if (input.startsWith("1")) {
    			exit = true;
    		}
		}
    }
	
	
	void dailyTaskRecordDeliveries() {
		boolean exit = false;
		while(!exit) {
			System.out.println("Select option");
			System.out.println("1  Enter Order ID");
			System.out.println("2. Go Back");
			
			String input = reader.nextLine().trim();
			if(input.startsWith("1")) {
				System.out.println("Enter Comma Separated order ids");
				String orderidS = reader.nextLine().trim();
				orderidS = orderidS.replaceAll("\\s","");
				String orderidlist[] = orderidS.split(",");
				Order.updateOrderlist(orderidlist, conn);
				
			}else if (input.startsWith("2")) {
				exit = true;
			}else {
				System.out.println("Invalid Option. Try Again");
			}
		}
	}
	
	void dailyTaskUpdateInventory() {
		PreparedStatement pstmt = null; 
		ResultSet rs=null;
		try {
			pstmt = this.conn.prepareStatement("select P_ID,MIN_ORDER,(THRESHOLD_QUANTITY - QUANTITY) AS DIFF from INVENTORY where QUANTITY<THRESHOLD_QUANTTIY");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String id=rs.getString(1);
				int min_ord = rs.getInt(2);
				Order new_order = new Order(this.service_center);
				if(rs.getInt(3)>min_ord) {
				new_order.placeOrder(id, Integer.toString(rs.getInt(3)), conn);
			    }
				else {
				new_order.placeOrder(id, Integer.toString(min_ord), conn);	
				}
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Mechanic extends Employee implements HourlyPayable{
	Mechanic(String ID, Connection conn, int flag){
		super(ID, conn, flag);
	}
	
	Mechanic(Person p, Connection conn){
		super(p, conn);
	}
	
	Mechanic(Employee emp, Connection conn){
		super(emp, conn);
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
