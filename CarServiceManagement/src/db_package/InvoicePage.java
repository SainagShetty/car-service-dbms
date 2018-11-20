package db_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	ArrayList<String> basicTaskIds, p_name;
	List<Integer> partsQuantity;
	float totalCost;
	float laborTime;
	float wageHr1, wageHr2;
	
	
	InvoicePage(Connection con, String srID){
		this.totalCost = 0;
		this.laborTime = 0;
		this.srID = srID;
		this.con = con;
		this.wageHr1 = 0;
		this.wageHr2 = 0;
		partsQuantity = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String query = "SELECT service.c_id, service.license_no, service.end_time, "
					+ "CASE WHEN service.maintenance_type is NULL THEN basicfaults.faults "
					+ "ELSE service.maintenance_type END AS service_type, service.start_date, "
					+ "employee.e_name, vehicle.make, vehicle.model FROM employee, service FULL "
					+ "OUTER JOIN basicfaults ON (basicfaults.basic_fid = service.basic_fid) FULL "
					+ "OUTER JOIN vehicle ON (service.license_no = vehicle.license_no) WHERE "
					+ "service.e_id = employee.e_id and service.ser_id = ?";
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
//		System.out.println(totalCost);
//		System.out.println(laborTime);
	}
	
	void fetchPrice() {
		basicTaskIds = new ArrayList<String>();
		p_name = new ArrayList<String>();
		
//		System.out.println(this.srID+this.licenceNo+this.licenceNo+this.serviceType);
		String queryMaintenance = "SELECT maintenance.basic_taskid FROM maintenance "
				+ "WHERE maintenance.type = ? "
				+ "and maintenance.make = ( SELECT vehicle.make FROM vehicle "
				+ "WHERE vehicle.license_no = ?) and maintenance.model = "
				+ "( SELECT vehicle.model FROM vehicle WHERE vehicle.license_no = ?)";
		String queryRepair = "SELECT faults.basic_taskid FROM faults, service "
				+ "WHERE service.basic_fid = faults.basic_fid and service.ser_id = ?";
		char maintenance_type = 'R';
		if(this.serviceType.startsWith("A") && this.serviceType.length() == 1) {
			maintenance_type = 'A';
		}
		else if(this.serviceType.startsWith("B") && this.serviceType.length() == 1) {
			maintenance_type = 'B';
		}
		else if(this.serviceType.startsWith("C") && this.serviceType.length() == 1) {
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
			try{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				pstmt = con.prepareStatement("Select diagnostic_fee from basicfaults where faults=?");
				pstmt.setString(1, this.serviceType);
				rs = pstmt.executeQuery();
				while(rs.next())  {
					this.totalCost += rs.getInt(1);
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
				pstmt.setString(1, "C");
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
				pstmt.setString(1, "B");
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
				pstmt.setString(1, "A");
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
//			System.out.println(basicTaskIds.get(i)+this.licenceNo+this.licenceNo);
			String query2 = "select tasks.charge_rate, tasks.time_required, tasks.part_quantity, parts.unit_price, parts.p_name "
					+ "from tasks full outer join parts on (tasks.p_id = parts.p_id) "
					+ "where tasks.basic_taskid = ? and tasks.make = "
					+ "( SELECT vehicle.make FROM vehicle WHERE vehicle.license_no = ?) "
					+ "and tasks.model = ( SELECT vehicle.model FROM vehicle WHERE "
					+ "vehicle.license_no = ?)";
			try{
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				pstmt = con.prepareStatement(query2);
				pstmt.setString(1, basicTaskIds.get(i));
				pstmt.setString(2, this.licenceNo);
				pstmt.setString(3, this.licenceNo);
				rs = pstmt.executeQuery();
//				System.out.println(basicTaskIds.get(i));
				while(rs.next())  {
					
					float chargeRate = Float.parseFloat(rs.getString(1));
					float time_required = rs.getFloat(2);
					int part_quantity = rs.getInt(3);
					float unit_price = Float.parseFloat(rs.getString(4));
					this.p_name.add(rs.getString(5));
					this.partsQuantity.add(part_quantity);
					this.totalCost += part_quantity*unit_price;
					this.totalCost += chargeRate*time_required;
					this.laborTime += time_required; 
					
					if(this.wageHr1 == 0) {
						this.wageHr1 = chargeRate;
					}
					else if(this.wageHr1 != chargeRate) {
						this.wageHr2 = chargeRate;
					}
				}
				
//				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	
	void printInvoices() {
		System.out.println("A. Service ID: " + this.srID);
		System.out.println("B. Service Start Date/Time: " + this.serviceStart);
		System.out.println("C. Service End Date/Time: " + this.serviceEnd);
		System.out.println("D. Licence Plate: " + this.licenceNo);
		System.out.println("E. Service Type: " + this.serviceType);
		System.out.println("F. Mechanic Name: " + this.mechanicName);
		System.out.println("G. Total Service Cost: " + this.totalCost);
	}
	void printInvoicesDetailed() {
		System.out.println("A. Service ID: " + this.srID);
		System.out.println("B. Service Start Date/Time: " + this.serviceStart);
		System.out.println("C. Service End Date/Time: " + this.serviceEnd);
		System.out.println("D. Licence Plate: " + this.licenceNo);
		System.out.println("E. Service Type: " + this.serviceType);
		System.out.println("F. Mechanic Name: " + this.mechanicName);
		System.out.println("G. Parts Used: ");
		for(int i=0;i<this.p_name.size();i++) {
			System.out.println("\t" + this.p_name.get(i) + " : " + this.partsQuantity.get(i));
		}
		System.out.println("H. Total labor hours: " + this.laborTime);
		System.out.println("H. Labor wages per hour: " + this.wageHr1);
		if(this.wageHr2 != 0) {
			System.out.print(this.wageHr2);
		}
		System.out.println("G. Total Service Cost: " + this.totalCost);
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("UPDATE SERVICE SET totalCost = ? WHERE ser_id = ?");
			pstmt.setFloat(1, this.totalCost);
			pstmt.setString(2, this.srID);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pstmt = null;
		try {
			pstmt = con.prepareStatement("UPDATE SERVICE SET laborTime = ? WHERE ser_id = ?");
			pstmt.setFloat(1, this.laborTime);
			pstmt.setString(2, this.srID);
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
