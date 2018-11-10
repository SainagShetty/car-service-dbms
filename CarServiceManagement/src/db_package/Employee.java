package src.db_package;

import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

class Employee extends Person {
	int service_center; 
	String e_address;
    String  e_tel_no;
	String start_date;
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

    Employee(String userID,String emailID, String password, int my_role, int sc_id,
    		 String e_address, String e_tel_no, String start_date, int compensation, Connection conn) {	
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
		//login(e_address, e_address);
	}
	
	Manager(int emp_id, Connection conn){
		super(emp_id, conn);
	}
	
	Manager(String userID,String emailID, String password, int sc_id,
   		 String e_address, String e_tel_no, String start_date, int compensation, Connection conn){
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
		
		
		
	}

}


class Receptionist extends Employee implements MonthlyPayable{
	
	Receptionist(String emailID, Connection conn){
		super(emailID, conn);
	}
	Receptionist(Person p, Connection conn){
		super(p);
		//get other details from database
	}
	
	Receptionist(int emp_id, Connection conn){
		super(emp_id, conn);
	}
	
	Receptionist(String userID,String emailID, String password, int sc_id,
   		 String e_address, String e_tel_no, String start_date, int compensation, Connection conn){
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
	}
	Mechanic(int emp_id, Connection conn){
		super(emp_id, conn);
	}
	
	Mechanic(String userID,String emailID, String password, int sc_id,
   		 String e_address, String e_tel_no, String start_date, int compensation, Connection conn){
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
