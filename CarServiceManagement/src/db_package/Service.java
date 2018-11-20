package db_package;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class Service {
	String ser_id;
	String e_id;
	String sc_id;
	String c_id;
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
	
	Maintenance(String sc_id, String c_email, String Vehicle_license, float current_mileage, String mechanic_name, Connection conn) {
		super(sc_id, c_email, Vehicle_license, current_mileage, mechanic_name, conn);
		
		this.conn = conn;
		
		Vehicle vehicle = new Vehicle(Vehicle_license);
		this.make = vehicle.getMake();
		this.model = vehicle.getModel();
		
		Customer customer = new Customer(c_email, this.conn);
		this.c_id = customer.getCustomerID();
		
		String service_type = this.maintnanceType(this.make, this.model, current_mileage);
		ArrayList<String> basicTasks = this.maintnanceTask(this.make, this.model, service_type);
		
		ArrayList<String> partID = new ArrayList<String>();
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		ArrayList<String> missing = new ArrayList<String>();
		float totalTime = 0;
		for (String value : basicTasks) {
			ArrayList<String> temp = this.fetchTaskDetails(this.make, this.model, value);
			totalTime += Float.parseFloat(temp.get(0));
			partID.add(temp.get(1));
			quantity.add(Integer.parseInt(temp.get(2)));
			boolean check = this.checkInventory(sc_id, temp.get(1), Integer.parseInt(temp.get(2)));
			if(!check) {
				missing.add(temp.get(1));
			}
		}
//		System.out.println("Total time "+totalTime);
		if(missing.size() == 0) {
//			System.out.println("Schedule Service");
			ArrayList<String> currentWeekMap = new ArrayList<String>();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

			LocalDate today = LocalDate.now();
			int count = 0;
			for (int i=1; count < 7; i++) {
			    LocalDate newDay = today.plusDays(i);
			    String dayOfWeek = newDay.getDayOfWeek().toString();
//			    System.out.println("Day of the week" +dayOfWeek);
			    if(dayOfWeek.equals("SATURDAY") || dayOfWeek.equals("SUNDAY")) {
			    	continue;
			    }
			    String formattedDate = newDay.format(formatter);
			    currentWeekMap.add(formattedDate);
			    count++;
			}

//			System.out.println(currentWeekMap);
			
			
			if(mechanic_name.equals("")) {
				
				ArrayList<String> mech = new ArrayList<String>();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try{
					pstmt = conn.prepareStatement("SELECT E_ID from EMPLOYEE where e_role = 'Mechanic' and sc_id = ?");
					pstmt.setString(1, this.sc_id);
					rs = pstmt.executeQuery();
					while(rs.next())  {
						mech.add(rs.getString(1));
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
				//Find next two possible date
				ArrayList<String> availableDates = new ArrayList<String>();
				ArrayList<String> emp_id = new ArrayList<String>();
				for(String date_values: currentWeekMap) {
					for(String mech_values: mech) {
						try{
							pstmt = conn.prepareStatement("SELECT COUNT(*) from MECHANIC where e_id = ? and WORK_DATE = ?");
							pstmt.setString(1, mech_values);
							pstmt.setString(2, date_values);
							rs = pstmt.executeQuery();
							rs.next();
							if(rs.getInt(1) == 0) {
								availableDates.add(date_values);
								emp_id.add(mech_values);
								break;
							}
							else {
								pstmt = conn.prepareStatement("SELECT HOURS from MECHANIC where e_id = ? and WORK_DATE = ?");
								pstmt.setString(1, mech_values);
								pstmt.setString(2, date_values);
								rs = pstmt.executeQuery();
								rs.next();
								if(rs.getFloat(1)+totalTime <= 8.0)
								{
									availableDates.add(date_values);
									emp_id.add(mech_values);
									break;
								}
							}
							
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
				}
				
				System.out.println("1. "+availableDates.get(0));
				System.out.println("2. "+availableDates.get(1));
				
			}
			else {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String emp_id = "";
				try {
					pstmt = conn.prepareStatement("SELECT E_ID from EMPLOYEE where e_name=?");
					pstmt.setString(1, mechanic_name);
					rs = pstmt.executeQuery();
					rs.next();
					emp_id = rs.getString(1);
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				ArrayList<String> availableDates = new ArrayList<String>();
				for(String date_values: currentWeekMap) {
					try{
						pstmt = conn.prepareStatement("SELECT COUNT(*) from MECHANIC where e_id = ? and WORK_DATE = ?");
						pstmt.setString(1, emp_id);
						pstmt.setString(2, date_values);
						rs = pstmt.executeQuery();
						rs.next();
						if(rs.getInt(1) == 0) 
							availableDates.add(date_values);
						else {
							pstmt = conn.prepareStatement("SELECT HOURS from MECHANIC where e_id = ? and WORK_DATE = ?");
							pstmt.setString(1, emp_id);
							pstmt.setString(2, date_values);
							rs = pstmt.executeQuery();
							rs.next();
							if(rs.getFloat(1)+totalTime <= 8.0)
								availableDates.add(date_values);
						}
						
					}catch(SQLException e){
						e.printStackTrace();
					}
					
				}
				
				System.out.println("1. "+availableDates.get(0));
				System.out.println("2. "+availableDates.get(1));
			}
		}
		else {
			System.out.println("Parts missing, place an order");
		}
		
	}
	
	private String maintnanceType(String make, String model, float mileage) {
		int a = 0, b = 0, c = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT type, mileage from Maintenance where make = ? and model = ? GROUP BY type, MILEAGE");
			pstmt.setString(1, make);
			pstmt.setString(2, model);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				String type_s = rs.getString(1);
				if(type_s.equals("A"))
					a = rs.getInt(2)*1000;
				else if(type_s.equals("B"))
					b = rs.getInt(2)*1000;
				else if(type_s.equals("C"))
					c = rs.getInt(2)*1000;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
//		System.out.println("ABC"+a+" "+b+" "+c);
		
		int mileage_int = Math.round(mileage);
		int temp = mileage_int%c;
		if(temp < a)
			return "A";
		if(temp < b)
			return "B";
		return "C";
	}
	
	private ArrayList<String> maintnanceTask(String make, String model, String type) {
		ArrayList<String> basicTasks = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT BASIC_TASKID from Maintenance where make = ? and model = ? and type = ?");
			pstmt.setString(1, make);
			pstmt.setString(2, model);
			pstmt.setString(3, type);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				basicTasks.add(rs.getString(1));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
//		for (String value : basicTasks) {
//            System.out.println(value);
//        }
		return basicTasks;
	}
	
	private ArrayList<String> fetchTaskDetails(String make, String model, String taskid)
	{
		ArrayList<String> details = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT time_required, p_id, part_quantity from TASKS where make = ? and model = ? and basic_taskid = ?");
			pstmt.setString(1, make);
			pstmt.setString(2, model);
			pstmt.setString(3, taskid);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				details.add(rs.getString(1));
				details.add(rs.getString(2));
				details.add(rs.getString(3));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
//		for (String value : details) {
//	          System.out.println(value);
//	     }
		return details;
	}
	
	private boolean checkInventory(String sc_id, String part_id, int quantity) {
		boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT quantity from INVENTORY where p_id = ? and sc_id = ?");
			pstmt.setString(1, part_id);
			pstmt.setString(2, sc_id);
			rs = pstmt.executeQuery();
			while(rs.next())  {
//				System.out.println("Checking part "+part_id+" Quantity: "+quantity+" in "+rs.getInt(1));
				int temp_quantity = rs.getInt(1);
				if(temp_quantity > quantity)
					status = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		return status;
	}
	
}