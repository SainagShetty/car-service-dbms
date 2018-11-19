package db_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

public class InvoicePage{
	String srID;
	Date serviceStart;
	Date serviceEnd;
	String licenceNo;
	String serviceType;
	String mechanicName;
	String make;
	String model;
	float totalServiceCost;
	Connection con;
	String cID;
	ArrayList<String> basicTaskIds;
	
	InvoicePage(Connection con, String srID){
		this.srID = srID;
		this.con = con;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String query = "SELECT service.c_id, service.license_no, service.end_time, CASE WHEN service.maintenance_type is NULL THEN basicfaults.faults ELSE service.maintenance_type END AS service_type, service.start_date, employee.e_name, vehicle.make, vehicle.model FROM employee, service FULL OUTER JOIN basicfaults ON (basicfaults.basic_fid = service.basic_fid) FULL OUTER JOIN vehicle ON (service.license_no = vehicle.license_no) WHERE service.e_id = employee.e_id and service.ser_id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, this.srID);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.cID = rs.getString(1);
				this.licenceNo = rs.getString(2);
				this.serviceEnd = rs.getDate(3);
				this.serviceType = rs.getString(4);
				this.serviceStart = rs.getDate(5);
				this.mechanicName = rs.getString(6);
				this.make = rs.getString(7);
				this.model = rs.getString(8);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		fetchPrice();
	}
	
	void fetchPrice() {
		basicTaskIds = new ArrayList<String>();
		System.out.println(this.srID+this.licenceNo+this.licenceNo);
		String queryMaintenance = "SELECT maintenance.basic_taskid FROM maintenance "
				+ "WHERE maintenance.type = "
				+ "( SELECT service.maintenance_type FROM service WHERE service.ser_id = ?) "
				+ "and maintenance.make = ( SELECT vehicle.make FROM vehicle "
				+ "WHERE vehicle.license_no = ?) and maintenance.model = "
				+ "( SELECT vehicle.model FROM vehicle WHERE vehicle.license_no = ?)";
		String queryRepair = "SELECT faults.basic_taskid FROM faults, service "
				+ "WHERE service.basic_fid = faults.basic_fid and service.ser_id = ?";
		char maintenance_type = 'R';
		if(this.serviceType.startsWith("A")) {
			maintenance_type = 'A';
		}
		else if(this.serviceType.startsWith("B")) {
			maintenance_type = 'B';
		}
		else if(this.serviceType.startsWith("C")) {
			maintenance_type = 'C';
		}

		switch(maintenance_type) {
		case 'R':
//			System.out.println("I'm repair!!!");
			try{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				pstmt = con.prepareStatement(queryRepair);
				pstmt.setString(1, this.srID);
				rs = pstmt.executeQuery();
				while(rs.next())  {
					this.basicTaskIds.add(rs.getString(1));
				}
//				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			break;
		case 'C':
			
			try{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				pstmt = con.prepareStatement(queryMaintenance);
				pstmt.setString(1, this.srID);
				pstmt.setString(2, this.licenceNo);
				pstmt.setString(3, this.licenceNo);
				rs = pstmt.executeQuery();
				while(rs.next())  {
					this.basicTaskIds.add(rs.getString(1));
				}
//				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		case 'B':
//			System.out.println("I'm B!!!");
			try{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				pstmt = con.prepareStatement(queryMaintenance);
				pstmt.setString(1, this.srID);
				pstmt.setString(2, this.licenceNo);
				pstmt.setString(3, this.licenceNo);
				rs = pstmt.executeQuery();
				while(rs.next())  {
					this.basicTaskIds.add(rs.getString(1));
				}
//				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		case 'A':
//			System.out.println("I'm A!!!");
			try{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				pstmt = con.prepareStatement(queryMaintenance);
				pstmt.setString(1, this.srID);
				pstmt.setString(2, this.licenceNo);
				pstmt.setString(3, this.licenceNo);
				rs = pstmt.executeQuery();
				while(rs.next())  {
					this.basicTaskIds.add(rs.getString(1));
				}
//				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
//		System.out.println("Printing"+basicTaskIds.size());
		for(int i=0;i<basicTaskIds.size();i++) {
			System.out.println(basicTaskIds.get(i));
		}
	}
	
	void printInvoices() {
		
		
	}
}
