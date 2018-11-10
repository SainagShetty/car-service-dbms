package src.db_package;
import java.util.Date;
import java.sql.*;

class Vehicle {
	
	String model;
	String make;
	String license_no; //PK
	String date_of_purchase;
	int mileage;
	int c_id;
	int year;
	Connection conn;
	
	Vehicle(int licencse_no){
		// get from database;
	}
	
	Vehicle(String model, String make, String license_no, String date_of_purchase, int year, Connection conn){
		this.make = make;
		this.model = model;
		this.license_no = license_no;
		this.date_of_purchase = date_of_purchase;
		this.conn = conn;
		dbCreateVehicle();
	}
	
	void updateMileage(int mileage){
		this.mileage = mileage;
	}
	
	void setCustomer(int c_id) {
		this.c_id = c_id;
		// update c_id in table 
	}
	
	void dbCreateVehicle() {
		//create entry in database 
	}
	
	
}
