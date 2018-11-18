package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
	
	Manager(String emailID, Connection conn){
		super(emailID, conn);
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
	
	Manager(int emp_id, Connection conn){
		super(emp_id, conn);
	}
	
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
