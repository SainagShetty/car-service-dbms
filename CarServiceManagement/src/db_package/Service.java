package db_package;
import java.util.Date;
import java.sql.*;

abstract class Service {
	String ser_id;
	String e_id;
	String sc_id;
	String c_id;
	String vehicle_license;
	int serviceCenter_id;
	//Convert into date
	String startDate;
	//Convert into date
	String endDate;
	int Status;
	
	Service(String ser_id, Connection conn){
		this.ser_id = ser_id;
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String s_temp = "select * from Service where SER_ID='"+ser_id+"'";
//			System.out.println(s_temp);
			ResultSet rs=stmt.executeQuery(s_temp);
			rs.next();
			this.e_id = rs.getObject(2) == null ? "NULL" : rs.getObject(2).toString();
			this.c_id = rs.getObject(3) == null ? "NULL" : rs.getObject(3).toString();
			this.sc_id = rs.getObject(4) == null ? "NULL" : rs.getObject(4).toString();
			this.vehicle_license = rs.getObject(5) == null ? "NULL" : rs.getObject(5).toString();
			this.endDate = rs.getObject(6) == null ? "NULL" : rs.getObject(6).toString();
			this.startDate = rs.getObject(9) == null ? "NULL" : rs.getObject(9).toString();
			
//			System.out.println(" "+this.ser_id+" "+ this.e_id+" "+this.c_id+" "+this.sc_id+" "+this.vehicle_license+" "+this.endDate+" "+this.startDate);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	Service(String c_id, String Vehicle_license, String sc_id, Connection conn){
		
		// if exists in Service table, fetch from DB
		// or create new
		this.c_id = c_id;
		this.vehicle_license = Vehicle_license;
		this.sc_id = sc_id;
		dbCreateService();
	}
	
	void startService() {
//		this.startDate = new Date();	
		// update db
		
	}
	
	void endService() {
//		this.endDate = new Date();
		//update db
	}
	
	void dbCreateService() {
		// create database entry
		// set s_id;
		this.sc_id = "1";
	}
}

class Repair extends Service{
	String problem;
	
	Repair(String s_id, Connection conn){
		super(s_id, conn);	
	}
	
	Repair(String c_id, String Vehicle_license, String sc_id, Connection conn){
		super(c_id,Vehicle_license, sc_id, conn);
	}

	void addProblem(String problem) {
		this.problem = problem; // can create constants 
	}
	
	void runDiagnosis() {
		//
		String fault = "New Fault x";
//		Report new_report = new Report(this.c_id, this.vehicle_license, this.serviceCenter_id);
		
//		new_report.addFaults(fault);
//		new_report.generateReport();
	}
	
	void repairMenu() {
		
	}
}

class Maintenance extends Service{

	Maintenance(String c_id, String Vehicle_license, String sc_id, Connection conn) {
		super(c_id, Vehicle_license, sc_id, conn);
		// TODO Auto-generated constructor stub
	}
	
}