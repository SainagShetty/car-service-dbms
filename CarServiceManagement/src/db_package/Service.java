package db_package;
import java.util.Date;
import java.sql.*;

abstract class Service {
	String ser_id;
	String e_id;
	String sc_id;
	String c_email;
	String vehicle_license;
	Date startDate;
	Date endDate;
	int Status;
	float milage;
	String make;
	String model;
	Connection conn;
	
	Service(Connection conn){
		 this.conn = conn;
	}
	
	Service(String sc_id, String c_email, String Vehicle_license, float current_milage, String mechanic_name, Connection conn){
		this.conn = conn;
		this.sc_id = sc_id;
		this.c_email = c_email;
		this.vehicle_license = Vehicle_license;
		this.milage = current_milage;
		
//		dbCreateService();
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
//	String problem;
//	
	Repair(String s_id, Connection conn){
		super(conn);	
	}
//	
//	Repair(String c_id, String Vehicle_license, String sc_id, Connection conn){
////		super(c_id,Vehicle_license, sc_id, conn);
//	}
//
//	void addProblem(String problem) {
//		this.problem = problem; // can create constants 
//	}
//	
//	void runDiagnosis() {
//		//
//		String fault = "New Fault x";
////		Report new_report = new Report(this.c_id, this.vehicle_license, this.serviceCenter_id);
//		
////		new_report.addFaults(fault);
////		new_report.generateReport();
//	}
//	
//	void repairMenu() {
//		
//	}
}

class Maintenance extends Service{
	
	Connection conn;
	
	Maintenance(String sc_id, String c_email, String Vehicle_license, float current_milage, String mechanic_name, Connection conn) {
		super(sc_id, c_email, Vehicle_license, current_milage, mechanic_name, conn);
		
		this.conn = conn;
		
		Vehicle vehicle = new Vehicle(Vehicle_license);
		this.make = vehicle.getMake();
		this.model = vehicle.getModel();
		
		Customer customer = new Customer(c_email, this.conn);
		
	}
	
}