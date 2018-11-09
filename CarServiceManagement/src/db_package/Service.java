package db_package;
import java.util.Date;
import java.sql.*;

abstract class Service {
	int s_id;
	int c_id;
	String vehicle_license;
	int serviceCenter_id;
	Date startDate;
	Date endDate;
	int Status;
	
	Service(int s_id){
		// fetch from DB 
	}
	
	Service(int c_id, String Vehicle_license, int sc_id){
		
		// if exists in Service table, fetch from DB
		// or create new
		this.c_id = c_id;
		this.vehicle_license = Vehicle_license;
		this.serviceCenter_id = sc_id;
		dbCreateService();
	}
	
	void startService() {
		this.startDate = new Date();	
		// update db
	}
	
	void endService() {
		this.endDate = new Date();
		//update db
	}
	
	void dbCreateService() {
		// create database entry
		// set s_id;
		this.s_id = 1;
	}
}

class Repair extends Service{
	String problem;
	
	Repair(int s_id){
		super(s_id);	
	}
	
	Repair(int c_id, String Vehicle_license, int sc_id){
		super(c_id,Vehicle_license, sc_id);
	}

	void addProblem(String problem) {
		this.problem = problem; // can create constants 
	}
	
	void runDiagnosis() {
		//
		String fault = "New Fault x";
		Report new_report = new Report(this.c_id, this.vehicle_license, this.serviceCenter_id);
		
		new_report.addFaults(fault);
		new_report.generateReport();
	}
	
	void repairMenu() {
		
	}
}