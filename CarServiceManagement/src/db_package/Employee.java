package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
 
class Employee extends Person {
	String eid;
	String e_name;
	String service_center; 
	String e_address;
    String  e_tel_no;
	Date start_date;
	int compensation;
	static final int withid = 1;
	static final int withemail = 2;
	
	Employee(Person p){
		super(p);
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
    	// TODO
    		// create entry in Employee table entry
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

    
}



class Manager extends Employee implements MonthlyPayable{
	
	Scanner reader;
	Manager(String ID, Connection conn, int flag){
		super(ID, conn, flag);
	}
	// used during login
	Manager(Person p, Connection conn){
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
	
//	Manager(int emp_id, Connection conn){
//		super(emp_id, conn);
//	}
	
	Manager(String userID,String emailID, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		super(userID, emailID,password, Role.MANAGER, sc_id,
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
	
	
	void managerMenu() {
		System.out.println(this.e_name);
		Boolean exit = false;
		
		while(!exit) {
    		
        	System.out.println("### Manager landing page ###");
    		System.out.println("1.  Profile");
    		System.out.println("2.  View Customer\n" + 
    				"Profile");
    		System.out.println("3. Add New\n" + 
    				"Employees");
    		System.out.println("4.  Payroll");
    		System.out.println("5.  Inventory");
    		System.out.println("6.  Orders");
    		System.out.println("7.  Notifications");
    		System.out.println("8.  New Car Model");
    		System.out.println("9.  Car Service Details");
    		System.out.println("10. Service History");
    		System.out.println("11. Invoices");
    		System.out.println("12. Logout");
    		
    		String input = reader.nextLine();
    		if (input.startsWith("1")) {
    			this.profilePage();
    		} else if (input.startsWith("2")) {
    			System.out.println("Enter Customer Email");
    			String email = reader.nextLine();
    			Customer cus = new Customer(email, conn);
    			cus.displayProfile();
    		} else if (input.startsWith("3")){
    			
    			this.addEmployee();
    		}else if (input.startsWith("4")){
    			this.payrollPage();
    		}else if (input.startsWith("5")){
    			signout();
    			exit = true;
    		} else {
    			exit = true;
    		}
    	}	
		
	}
	private void profilePage() {
		
	}
	 
	private void servicePage() {
		
	}
	
	private void invoicePage() {
		
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
						Receptionist newRecp = new Receptionist(emailad, emailad , "12345678", this.service_center,
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
					
					Mechanic newMech = new Mechanic(emailad, emailad , "12345678", this.service_center,
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
			    	Receptionist recEmp = new  Receptionist(emp);
			    	
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
	
}

class Receptionist extends Employee implements MonthlyPayable{
	
	Receptionist(String ID, Connection conn, int flag){
		super(ID, conn, flag);
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
	
	
	
	Receptionist(String userID,String emailID, String password, String sc_id,
   		 String e_address, String e_tel_no, Date start_date, int compensation, Connection conn){
		
		super(userID, emailID,password, Role.RECEPTIONIST, sc_id,
		   		e_address, e_tel_no, start_date, compensation, conn);	
		

		
	}
	
	
	
	public Receptionist(Employee emp) {
		super(emp);
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

	@Override
	public void displayPayroll() {
		// TODO Auto-generated method stub
		
	}
}
