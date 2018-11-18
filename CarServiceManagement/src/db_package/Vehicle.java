package db_package;
import java.util.Date;
import java.sql.*;

class Vehicle {
	
	String model;
	String make;
	String license_no; //PK
	Date date_of_purchase;
	int mileage;
	String c_id;
	int year;
	Date last_service_date;
	
	Connection conn;
	
	Vehicle(String license_no){
		// get from database;
		DBConnection dbConnection = DBConnection.getDBConnection();
		try{  
			conn = dbConnection.createConnection();
		}catch(Exception e){ System.out.println(e);}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT model, make, date_of_purchase, mileage, year, last_service_date FROM Vehicle WHERE license_no=?");
			pstmt.setString(1, license_no);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.model = rs.getString(1); 
				this.make = rs.getString(2);  
				this.date_of_purchase = rs.getDate(3); 
				this.mileage = rs.getInt(4);
				this.year = rs.getInt(5);
				this.last_service_date = rs.getDate(6);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		this.license_no = license_no;
	}
	
	Vehicle(String model, String make, String license_no, Date date_of_purchase, int year, 
			Connection conn, String c_id, int mileage, Date last_service_date){
		this.c_id = c_id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.license_no = license_no;
		this.mileage = mileage;
		this.date_of_purchase = date_of_purchase;
		this.conn = conn;
		this.last_service_date = last_service_date;
		dbCreateVehicle();
	}
	
	void updateMileage(int mileage){
		this.mileage = mileage;
	}
	
	void setCustomer(String c_id) {
		this.c_id = c_id;
		// update c_id in table 
	}
	
	void dbCreateVehicle() {
		//create entry in database 
		PreparedStatement pstmt = null;
		try{ 	
			pstmt = conn.prepareStatement("INSERT INTO Vehicle Values(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, this.model);
			pstmt.setString(2, this.make);
			pstmt.setString(1, this.license_no);
			pstmt.setDate(2, (java.sql.Date) this.date_of_purchase);
			pstmt.setInt(1, this.mileage);
			pstmt.setString(2, this.c_id);
			pstmt.setInt(1, this.year);
			pstmt.setDate(2, (java.sql.Date) this.last_service_date);
			pstmt.executeQuery();
			if(pstmt.executeUpdate() == 0){
				// Failure
				System.out.println("Error");
			}
			System.out.println("Works");
		}catch(SQLException e){}
	}
	
	void vehicleProfile() {
		System.out.println(this.license_no + ", " + this.make + ", " + this.model + ", " + this.year + ", purchased " + this.date_of_purchase + ", Latest Service Info: " + this.mileage + " miles, Last service on " + this.last_service_date );
//		XYZ-5643, Honda, Civic, 2009, purchased 24-Dec-2009. Latest service info: 90452 miles, Service C on 10-Sep-2018
	}
}
