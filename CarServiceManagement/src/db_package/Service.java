package db_package;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
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
	static int service_count = 1000;
	
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
	
	// Called only by Receptionist
	static void serviceHistory(String cus_email, Connection conn) {
		Customer cus = new Customer(cus_email, conn);
		String cus_id = cus.getCustomerID();
		System.out.println("CustomerID " + cus_id);
		
	 	boolean status = false;
	 	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM Service WHERE c_id=?");
			pstmt.setString(1, cus_id);
			rs = pstmt.executeQuery();

		while(rs.next())  {
						
						System.out.println("Service History for Customer");
						String service_id = rs.getString(1);
						String employee_id = rs.getString(2);
						String c_id = rs.getString(3);
						String sc_id = rs.getString(4);
						String License_no = rs.getString(5);
						Date endtime = rs.getDate(6);
						String BasicFaults = rs.getString(7);
					    String maintnType = rs.getString(8);
					    Date startDate = rs.getDate(9);
					    String laborTime = rs.getString(10);
					    String TotalCost = rs.getString(11);
					    String ser_status = "PENDING";
					    
					    System.out.println(endtime);
					    Date today = new Date(0);
					    
					    if(startDate.after(today) ) {
					    		ser_status = "ONGOING";	
					    } else if (endtime ==null || endtime.after(today) ) {
					    		ser_status = "PENDING";	
					    } else {
					    		ser_status = "COMPLETED";	
					    }
					    
					    Mechanic mech = new Mechanic(employee_id,conn,Employee.withid);
					    
			    			System.out.println("ServiceID: " + service_id);
			    			System.out.println("LicensePlate: " + License_no);
			    			System.out.println("ServiceType: " + maintnType);
			    			System.out.println("MechanicName: " + mech.emailID);
			    			System.out.println("ServiceStart " + startDate);
			    			System.out.println("ServiceEnd " + endtime);
			    			System.out.println("Service Status " + ser_status);
			    		
			    			status = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		if(!status)
		{
			System.out.println("Entered email has no service history");
		}
		
		//TODO print service history for the cus_email.
//		A. ServiceID
//		B. LicensePlate
//		C. ServiceType
//		D. MechanicName
//		E. ServiceStart
//		Date/Time
//		F. Service End
//		Date/Time (expected or actual)
//		G. Service Status (Pending,
//		Ongoing, or Complete)
	}
	
	
	// Called only by Customer
		static void serviceHistory( Connection new_con, String cus_id) {
			
			System.out.println("CustomerID " + cus_id);
			
		 	boolean status = false;
		 	PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			if (new_con == null) {
				System.out.println("null conn");
			}
			
			try {
				pstmt = new_con.prepareStatement("SELECT * FROM Service WHERE c_id=?");
				pstmt.setString(1, cus_id);
				rs = pstmt.executeQuery();

			while(rs.next())  {
							
							System.out.println("Service History for Customer");
							String service_id = rs.getString(1);
							String employee_id = rs.getString(2);
							String c_id = rs.getString(3);
							String sc_id = rs.getString(4);
							String License_no = rs.getString(5);
							Date endtime = rs.getDate(6);
							String BasicFaults = rs.getString(7);
						    String maintnType = rs.getString(8);
						    Date startDate = rs.getDate(9);
						    String laborTime = rs.getString(10);
						    String TotalCost = rs.getString(11);
						    String ser_status = "PENDING";
						    
						    System.out.println(endtime);
						    Date today = new Date(0);
						    
						    if(startDate.after(today) ) {
						    		ser_status = "ONGOING";	
						    } else if (endtime ==null || endtime.after(today) ) {
						    		ser_status = "PENDING";	
						    } else {
						    		ser_status = "COMPLETED";	
						    }
						    
						    Mechanic mech = new Mechanic(employee_id,new_con,Employee.withid);
						    
				    			System.out.println("ServiceID: " + service_id);
				    			System.out.println("LicensePlate: " + License_no);
				    			System.out.println("ServiceType: " + maintnType);
				    			System.out.println("MechanicName: " + mech.emailID);
				    			System.out.println("ServiceStart " + startDate);
				    			System.out.println("ServiceEnd " + endtime);
				    			System.out.println("Service Status " + ser_status);
				    		
				    			status = true;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();

			}
			
			if(!status)
			{
				System.out.println("Entered email has no service history");
			}
			
			//TODO print service history for the cus_email.
//			A. ServiceID
//			B. LicensePlate
//			C. ServiceType
//			D. MechanicName
//			E. ServiceStart
//			Date/Time
//			F. Service End
//			Date/Time (expected or actual)
//			G. Service Status (Pending,
//			Ongoing, or Complete)
		}
		
	
	
	static ArrayList<String> serviceHistory(String cus_email, boolean flag,  Connection conn) {
		Customer cus = new Customer(cus_email, conn);
		String cus_id = cus.getCustomerID();
		System.out.println("CustomerID " + cus_id);
		int count = 0;
		ArrayList<String> serviceID = new ArrayList<String>();
	 	boolean status = false;
	 	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM Service WHERE c_id=?");
			pstmt.setString(1, cus_id);
			rs = pstmt.executeQuery();

		while(rs.next())  {
						
						System.out.println("Service History for Customer");
						String service_id = rs.getString(1);
						String employee_id = rs.getString(2);
						String c_id = rs.getString(3);
						String sc_id = rs.getString(4);
						String License_no = rs.getString(5);
						Date endtime = rs.getDate(6);
						String BasicFaults = rs.getString(7);
					    String maintnType = rs.getString(8);
					    Date startDate = rs.getDate(9);
					    String laborTime = rs.getString(10);
					    String TotalCost = rs.getString(11);
					    String ser_status = "PENDING";
					    
					    System.out.println(endtime);
					    Date today = new Date(0);
					    
					    if(startDate.after(today) ) {
					    		ser_status = "ONGOING";	
					    } else if (endtime ==null || endtime.after(today) ) {
					    		ser_status = "PENDING";	
					    } else {
					    		ser_status = "COMPLETED";	
					    }
					    
					    Mechanic mech = new Mechanic(employee_id,conn,Employee.withid);
					    	if(ser_status.equals("ONGOING")) {
					    		System.out.println("Service "+count);
					    		count++;
				    			System.out.println("ServiceID: " + service_id);
				    			serviceID.add(service_id);
				    			System.out.println("LicensePlate: " + License_no);
				    			System.out.println("ServiceType: " + maintnType);
				    			System.out.println("MechanicName: " + mech.emailID);
				    			System.out.println("ServiceStart " + startDate);
				    			System.out.println("ServiceEnd " + endtime);
				    			System.out.println("Service Status " + ser_status);
					    	}
			    			status = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		if(!status)
		{
			System.out.println("Entered email has no service history");
		}
		return serviceID;
		//TODO print service history for the cus_email.
//		A. ServiceID
//		B. LicensePlate
//		C. ServiceType
//		D. MechanicName
//		E. ServiceStart
//		Date/Time
//		F. Service End
//		Date/Time (expected or actual)
//		G. Service Status (Pending,
//		Ongoing, or Complete)
	}
	
	// Called only by Manager
	static void serviceHistory(Connection conn, String sc_id, int byManager) {
		
		if (byManager == 1);
		
//		Display the following details for all cars that were serviced at this service center followed by the menu.
//		A. ServiceID
//		B. CustomerName
//		C. LicensePlate
//		D. ServiceType
//		E. MechanicName
//		F. Service Start
//		Date/Time
//		G. Service End
//		Date/Time (expected or actual)
//		H. ServiceStatus (Pending,
//		Ongoing, or Complete)
//		Customer cus = new Customer(cus_email, conn);
//		String cus_id = cus.getCustomerID();
//		System.out.println("CustomerID " + cus_id);
		
	 	boolean status = false;
	 	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM Service WHERE sc_id=?");
			pstmt.setString(1, sc_id);
			rs = pstmt.executeQuery();

		while(rs.next())  {
						
						System.out.println("Service History for Service Center");
						String service_id = rs.getString(1);
						String employee_id = rs.getString(2);
						String c_id = rs.getString(3);
						String scid = rs.getString(4);
						String License_no = rs.getString(5);
						Date endtime = rs.getDate(6);
						String BasicFaults = rs.getString(7);
					    String maintnType = rs.getString(8);
					    Date startDate = rs.getDate(9);
					    String laborTime = rs.getString(10);
					    String TotalCost = rs.getString(11);
					    String ser_status = "PENDING";
					    
					    System.out.println(endtime);
					    Date today = new Date(0);
					    
					    if(startDate.after(today) ) {
					    		ser_status = "ONGOING";	
					    } else if (endtime ==null || endtime.after(today) ) {
					    		ser_status = "PENDING";	
					    } else {
					    		ser_status = "COMPLETED";	
					    }
					    
					    Mechanic mech = new Mechanic(employee_id,conn,Employee.withid);
					    
			    			System.out.println("ServiceID: " + service_id);
			    			System.out.println("LicensePlate: " + License_no);
			    			System.out.println("ServiceType: " + maintnType);
			    			System.out.println("MechanicName: " + mech.e_name);
			    			System.out.println("ServiceStart " + startDate);
			    			System.out.println("ServiceEnd " + endtime);
			    			System.out.println("Service Status " + ser_status);
			    		
			    			status = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		//TODO print service history for the cus_email.
//		A. ServiceID
//		B. LicensePlate
//		C. ServiceType
//		D. MechanicName
//		E. ServiceStart
//		Date/Time
//		F. Service End
//		Date/Time (expected or actual)
//		G. Service Status (Pending,
//		Ongoing, or Complete)
	}
	
	static void getServiceType() {
		
	}
}

class Repair extends Service{
//	String problem;
//	
	Scanner reader;
Connection conn;
	Repair(String s_id, String emailID, String licensePlate, float temp_milage, String mechanic_name, Connection conn){
		super(s_id, emailID, licensePlate, temp_milage, mechanic_name, conn);
		Calendar calendar;
		SimpleDateFormat formatter_1;
		SimpleDateFormat formatter_2;
		SimpleDateFormat formatter_3;
		this.conn = conn;	
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "BF001");
		map.put(2, "BF002");
		map.put(3, "BF003");
		map.put(4, "BF004");
		map.put(5, "BF005");
		map.put(6, "BF006");
		map.put(7, "BF007");
		System.out.println("### Schedule Repair ###");
		System.out.println("1. Engine knock");
		System.out.println("2. Car drifts in a particular direction");
		System.out.println("3. Battery does not hold charge");
		System.out.println("4. Black/unclean exhaust");
		System.out.println("5. A/C-Heater not working");
		System.out.println("6. Headlamps/Tail lamps not working");
		System.out.println("7. Check engine light");
		System.out.println("8. Go back");
		System.out.println("\nEnter Choice (1-8)");
		reader = new Scanner(System.in);
		int repairNo = Integer.parseInt(reader.nextLine());
		if(repairNo != 8) {
			
		Vehicle vehicle = new Vehicle(licensePlate);
		this.make = vehicle.getMake();
		this.model = vehicle.getModel();
		
		Customer customer = new Customer(c_email, this.conn);
		this.c_id = customer.getCustomerID();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> basicTasks = new ArrayList<String>();
		try{
			String query = "SELECT basic_taskid "
					+ "FROM faults "
					+ "WHERE basic_fid = ?";
			pstmt = this.conn.prepareStatement(query);
			pstmt.setString(1, map.get(repairNo));
			rs = pstmt.executeQuery();
			while(rs.next())  {
				basicTasks.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ArrayList<String> partID = new ArrayList<String>();
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		ArrayList<String> missing = new ArrayList<String>();
		float totalTime = 0;
		float totalCost = 0;
		for (String value : basicTasks) {
			ArrayList<String> temp = this.fetchTaskDetails(this.make, this.model, value);
			totalTime += Float.parseFloat(temp.get(0));
			partID.add(temp.get(1));
			quantity.add(Integer.parseInt(temp.get(2)));
			totalCost += Float.parseFloat(temp.get(3));
			boolean check = this.checkInventory(sc_id, temp.get(1), Integer.parseInt(temp.get(2)));
			if(!check) {
				missing.add(temp.get(1));
			}
		}
//		System.out.println("Total time "+totalTime);
		if(missing.size() == 0) {
//			System.out.println("Schedule Service");
			ArrayList<java.sql.Date> currentWeekMap = new ArrayList<java.sql.Date>();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
			    formatter_1 = new SimpleDateFormat("yyyy-MM-dd");
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				  java.util.Date date = null;
				try {
					date = dateFormat.parse(formattedDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//				  System.out.println(sqlStartDate);
			    
			    currentWeekMap.add(sqlStartDate);
			    count++;
			}

//			System.out.println(currentWeekMap);
			
			
			if(mechanic_name.equals("")) {
				
				ArrayList<String> mech = new ArrayList<String>();
				PreparedStatement pstmt1 = null;
				ResultSet rs1 = null;
				try{
					pstmt1 = conn.prepareStatement("SELECT E_ID from EMPLOYEE where e_role = 'Mechanic' and sc_id = ?");
					pstmt1.setString(1, this.sc_id);
					rs1 = pstmt1.executeQuery();
					while(rs1.next())  {
						mech.add(rs1.getString(1));
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				//Find next two possible date
				ArrayList<java.sql.Date> availableDates = new ArrayList<java.sql.Date>();
				ArrayList<String> emp_id = new ArrayList<String>();
				ArrayList<Float> time_slot = new ArrayList<Float>();
				for(java.sql.Date date_values: currentWeekMap) {
					for(String mech_values: mech) {
						try{
							
							pstmt1 = conn.prepareStatement("SELECT COUNT(*) from MECHANIC where e_id = ? and WORKD_DATE = ?");
							pstmt1.setString(1, mech_values);
							pstmt1.setDate(2, date_values);
							rs1 = pstmt1.executeQuery();
							rs1.next();
							if(rs1.getInt(1) == 0) {
								availableDates.add(date_values);
								emp_id.add(mech_values);
								time_slot.add((float) 0);
								break;
							}
							else {
							
								pstmt1 = conn.prepareStatement("SELECT HOURS from MECHANIC where e_id = ? and WORKD_DATE = ?");
								pstmt1.setString(1, mech_values);
								pstmt1.setDate(2, date_values);
								rs1 = pstmt1.executeQuery();
								rs1.next();
								if(rs1.getFloat(1)+totalTime <= 8.0)
								{
									availableDates.add(date_values);
									emp_id.add(mech_values);
									time_slot.add(rs1.getFloat(1));
									break;
								}
							}
							
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
				}
				
				System.out.println("Select a Date");
				System.out.println("1. "+availableDates.get(0));
				System.out.println("2. "+availableDates.get(1));
				System.out.println("3. Go Back");
				boolean exit = false;
				while(!exit) {
					
					String input = reader.nextLine();
					if (input.startsWith("1")) {
						//Update Service Table
						
						formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(0));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(0);
						calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt1 = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt1.setString(1, "SR"+(service_count+1));
							pstmt1.setString(2, emp_id.get(0));
							pstmt1.setString(3, this.c_id);
							pstmt1.setString(4, this.sc_id);
							pstmt1.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
							 System.out.println("   "+timestamp);
							pstmt1.setTimestamp(6, timestamp);

							pstmt1.setString(7, map.get(repairNo));

							pstmt1.setString(8, null);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt1.setTimestamp(9, timestamp);
							pstmt1.setFloat(10, totalTime);
							pstmt1.setFloat(11, totalCost);
							pstmt1.executeQuery();
							if(pstmt1.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(0) == 0) {
				        	//INSERT
				        	try{
								pstmt1 = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt1.setString(1, emp_id.get(0));
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt1.setTimestamp(2, timestamp);
								pstmt1.setFloat(3, totalTime);
								pstmt1.executeQuery();
								if(pstmt1.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id.get(0));
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
//								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
//								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt1 = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt1.setFloat(1, quantity.get(z));
								pstmt1.setString(2, this.sc_id);
								pstmt1.setString(3, partID.get(z));
								pstmt1.executeQuery();
							
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
				        
		    		} else if(input.startsWith("2")) {
		    			formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(1));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(1);
						calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt1 = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt1.setString(1, "SR"+(service_count+1));
							pstmt1.setString(2, emp_id.get(1));
							pstmt1.setString(3, this.c_id);
							pstmt1.setString(4, this.sc_id);
							pstmt1.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
							 System.out.println("   "+timestamp);
							pstmt1.setTimestamp(6, timestamp);
							pstmt1.setString(7, map.get(repairNo));
							pstmt1.setString(8, null);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt1.setTimestamp(9, timestamp);
							pstmt1.setFloat(10, totalTime);
							pstmt1.setFloat(11, totalCost);
							pstmt1.executeQuery();
							if(pstmt1.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(1) == 0) {
				        	//INSERT
				        	try{
								pstmt1 = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt1.setString(1, emp_id.get(1));
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt1.setTimestamp(2, timestamp);
								pstmt1.setFloat(3, totalTime);
								pstmt1.executeQuery();
								if(pstmt1.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id.get(1));
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
//								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
//								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt1 = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt1.setFloat(1, quantity.get(z));
								pstmt1.setString(2, this.sc_id);
								pstmt1.setString(3, partID.get(z));
								pstmt1.executeQuery();
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
		    		} else if(input.startsWith("3")) {
		    			exit = true;
		    		}
					System.out.println("3.  Go Back");
				}
				
			}
			else {
				PreparedStatement pstmt1 = null;
				ResultSet rs1 = null;
				String emp_id = "";
				try {
					pstmt1 = conn.prepareStatement("SELECT E_ID from EMPLOYEE where e_name=?");
					pstmt1.setString(1, mechanic_name);
					rs1 = pstmt1.executeQuery();
					rs1.next();
					emp_id = rs1.getString(1);
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				ArrayList<java.sql.Date> availableDates = new ArrayList<java.sql.Date>();
				ArrayList<Float> time_slot = new ArrayList<Float>();
				for(java.sql.Date date_values: currentWeekMap) {
					try{
						pstmt1 = conn.prepareStatement("SELECT COUNT(*) from MECHANIC where e_id = ? and WORKD_DATE = ?");
						pstmt1.setString(1, emp_id);
						pstmt1.setDate(2, date_values);
						rs1 = pstmt1.executeQuery();
						rs1.next();
						if(rs1.getInt(1) == 0) {
							availableDates.add(date_values);
							time_slot.add((float) 0);
						}
						else {
							pstmt1 = conn.prepareStatement("SELECT HOURS from MECHANIC where e_id = ? and WORKD_DATE = ?");
							pstmt1.setString(1, emp_id);
							pstmt1.setDate(2, date_values);
							rs1 = pstmt1.executeQuery();
							rs1.next();
							if(rs1.getFloat(1)+totalTime <= 8.0)
							{
								availableDates.add(date_values);
								time_slot.add(rs.getFloat(1));
							}
						}
						
					}catch(SQLException e){
						e.printStackTrace();
					}
					
				}
				System.out.println("Select a Date");
				System.out.println("1. "+availableDates.get(0));
				System.out.println("2. "+availableDates.get(1));
				System.out.println("3. Go Back");
				
				boolean exit = false;
				while(!exit) {
					
					String input = reader.nextLine();
					if (input.startsWith("1")) {
						//Update Service Table
						
						formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(0));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(0);
						calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt1 = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt1.setString(1, "SR"+(service_count+1));
							service_count += 1;
							pstmt1.setString(2, emp_id);
							pstmt1.setString(3, this.c_id);
							pstmt1.setString(4, this.sc_id);
							pstmt1.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//							 System.out.println("   "+timestamp);
							pstmt1.setTimestamp(6, timestamp);
							pstmt1.setString(7, map.get(repairNo));
							pstmt1.setString(8, null);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt1.setTimestamp(9, timestamp);
							pstmt1.setFloat(10, totalTime);
							pstmt1.setFloat(11, totalCost);
							pstmt1.executeQuery();
							if(pstmt1.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(0) == 0) {
				        	//INSERT
				        	try{
								pstmt1 = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt1.setString(1, emp_id);
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt1.setTimestamp(2, timestamp);
								pstmt1.setFloat(3, totalTime);
								pstmt1.executeQuery();
								if(pstmt1.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id);
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
//								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt1 = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt1.setFloat(1, quantity.get(z));
								pstmt1.setString(2, this.sc_id);
								pstmt1.setString(3, partID.get(z));
								pstmt1.executeQuery();
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
				        
		    		} else if(input.startsWith("2")) {
		    									
						formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(1));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(1);
//						calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt1 = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt1.setString(1, "SR"+(service_count+1));
							service_count += 1;
							pstmt1.setString(2, emp_id);
							pstmt1.setString(3, this.c_id);
							pstmt1.setString(4, this.sc_id);
							pstmt1.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//							 System.out.println("   "+timestamp);
							pstmt1.setTimestamp(6, timestamp);
							pstmt1.setString(7, map.get(repairNo));
							pstmt1.setString(8, null);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt1.setTimestamp(9, timestamp);
							pstmt1.setFloat(10, totalTime);
							pstmt1.setFloat(11, totalCost);
							pstmt1.executeQuery();
							if(pstmt1.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(1) == 0) {
				        	//INSERT
				        	try{
								pstmt1 = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt1.setString(1, emp_id);
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt1.setTimestamp(2, timestamp);
								pstmt1.setFloat(3, totalTime);
								pstmt1.executeQuery();
								if(pstmt1.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id);
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
//								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt1 = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt1.setFloat(1, quantity.get(z));
								pstmt1.setString(2, this.sc_id);
								pstmt1.setString(3, partID.get(z));
								pstmt1.executeQuery();
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
		    		} else if(input.startsWith("3")) {
		    			exit = true;
		    		}
					System.out.println("3.  Go Back");
				}
			}
		}
		else {
			System.out.println("Parts missing, Order for part will be placed. Please check again after");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 3);
			System.out.println(dateFormat.format(cal.getTime()));
		}
		
	}
	}
	
	private ArrayList<String> fetchTaskDetails(String make, String model, String taskid)
	{
		ArrayList<String> details = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT time_required, p_id, part_quantity, Charge_rate from TASKS where make = ? and model = ? and basic_taskid = ?");
			pstmt.setString(1, make);
			pstmt.setString(2, model);
			pstmt.setString(3, taskid);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				details.add(rs.getString(1));
				details.add(rs.getString(2));
				details.add(rs.getString(3));
				details.add(rs.getString(4));
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
		Calendar calendar;
		SimpleDateFormat formatter_1;
		SimpleDateFormat formatter_2;
		SimpleDateFormat formatter_3;
		
		Scanner reader = new Scanner(System.in);
		
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
		float totalCost = 0;
		for (String value : basicTasks) {
			ArrayList<String> temp = this.fetchTaskDetails(this.make, this.model, value);
			totalTime += Float.parseFloat(temp.get(0));
			partID.add(temp.get(1));
			quantity.add(Integer.parseInt(temp.get(2)));
			totalCost += Float.parseFloat(temp.get(3));
			boolean check = this.checkInventory(sc_id, temp.get(1), Integer.parseInt(temp.get(2)));
			if(!check) {
				missing.add(temp.get(1));
			}
		}
//		System.out.println("Total time "+totalTime);
		if(missing.size() == 0) {
//			System.out.println("Schedule Service");
			ArrayList<java.sql.Date> currentWeekMap = new ArrayList<java.sql.Date>();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
			    formatter_1 = new SimpleDateFormat("yyyy-MM-dd");
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				  java.util.Date date = null;
				try {
					date = dateFormat.parse(formattedDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//				  System.out.println(sqlStartDate);
			    
			    currentWeekMap.add(sqlStartDate);
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
				ArrayList<java.sql.Date> availableDates = new ArrayList<java.sql.Date>();
				ArrayList<String> emp_id = new ArrayList<String>();
				ArrayList<Float> time_slot = new ArrayList<Float>();
				
				for(java.sql.Date date_values: currentWeekMap) {
					for(String mech_values: mech) {
						try{
							
							pstmt = conn.prepareStatement("SELECT COUNT(*) from MECHANIC where e_id = ? and WORKD_DATE = ?");
							pstmt.setString(1, mech_values);
							pstmt.setDate(2, date_values);
							rs = pstmt.executeQuery();
							rs.next();
							if(rs.getInt(1) == 0) {
								availableDates.add(date_values);
								emp_id.add(mech_values);
								time_slot.add((float) 0);
								break;
							}
							else {
							
								pstmt = conn.prepareStatement("SELECT HOURS from MECHANIC where e_id = ? and WORKD_DATE = ?");
								pstmt.setString(1, mech_values);
								pstmt.setDate(2, date_values);
								rs = pstmt.executeQuery();
								rs.next();
								if(rs.getFloat(1)+totalTime <= 8.0)
								{
									availableDates.add(date_values);
									emp_id.add(mech_values);
									time_slot.add(rs.getFloat(1));
									break;
								}
							}
							
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
				}
				System.out.println(availableDates);
				System.out.println(emp_id);
				System.out.println(time_slot);
				System.out.println("Select a Date");
				System.out.println("1. "+availableDates.get(0));
				System.out.println("2. "+availableDates.get(1));
				System.out.println("3. Go Back");
				boolean exit = false;
				while(!exit) {
					
					String input = reader.nextLine();
					if (input.startsWith("1")) {
						//Update Service Table
						
						formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(0));
						System.out.println(availableDates.get(0));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(0);
//						System.out.println(timeDiff);
				        calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt.setString(1, "SR"+(service_count+1));
							service_count += 1;
							pstmt.setString(2, emp_id.get(0));
							pstmt.setString(3, this.c_id);
							pstmt.setString(4, this.sc_id);
							pstmt.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//							 System.out.println("   "+timestamp);
							pstmt.setTimestamp(6, timestamp);
							pstmt.setString(7, null);
							pstmt.setString(8, service_type);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt.setTimestamp(9, timestamp);
							pstmt.setFloat(10, totalTime);
							pstmt.setFloat(11, totalCost);
							pstmt.executeQuery();
							if(pstmt.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(0) == 0) {
				        	//INSERT
				        	try{
								pstmt = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt.setString(1, emp_id.get(0));
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(2, timestamp);
								pstmt.setFloat(3, totalTime);
								pstmt.executeQuery();
								if(pstmt.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id.get(0));
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
//								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
//								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt.setFloat(1, quantity.get(z));
								pstmt.setString(2, this.sc_id);
								pstmt.setString(3, partID.get(z));
								pstmt.executeQuery();
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
				        
		    		} else if(input.startsWith("2")) {
		    			formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(1));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(1);
//						System.out.println(timeDiff);
						calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt.setString(1, "SR"+(service_count+1));
							service_count += 1;
							pstmt.setString(2, emp_id.get(1));
							pstmt.setString(3, this.c_id);
							pstmt.setString(4, this.sc_id);
							pstmt.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//							 System.out.println("   "+timestamp);
							pstmt.setTimestamp(6, timestamp);
							pstmt.setString(7, null);
							pstmt.setString(8, service_type);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt.setTimestamp(9, timestamp);
							pstmt.setFloat(10, totalTime);
							pstmt.setFloat(11, totalCost);
							pstmt.executeQuery();
							if(pstmt.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(0) == 0) {
				        	//INSERT
				        	try{
								pstmt = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt.setString(1, emp_id.get(1));
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(2, timestamp);
								pstmt.setFloat(3, totalTime);
								pstmt.executeQuery();
								if(pstmt.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id.get(1));
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt.setFloat(1, quantity.get(z));
								pstmt.setString(2, this.sc_id);
								pstmt.setString(3, partID.get(z));
								pstmt.executeQuery();
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
		    		} else if(input.startsWith("3")) {
		    			exit = true;
		    		}
					System.out.println("3.  Go Back");
				}
				
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
				ArrayList<java.sql.Date> availableDates = new ArrayList<java.sql.Date>();
				ArrayList<Float> time_slot = new ArrayList<Float>();
				for(java.sql.Date date_values: currentWeekMap) {
					try{
						pstmt = conn.prepareStatement("SELECT COUNT(*) from MECHANIC where e_id = ? and WORKD_DATE = ?");
						pstmt.setString(1, emp_id);
						pstmt.setDate(2, date_values);
						rs = pstmt.executeQuery();
						rs.next();
						if(rs.getInt(1) == 0) 
						{
							availableDates.add(date_values);
							time_slot.add((float) 0);
						}
						else {
							pstmt = conn.prepareStatement("SELECT HOURS from MECHANIC where e_id = ? and WORKD_DATE = ?");
							pstmt.setString(1, emp_id);
							pstmt.setDate(2, date_values);
							rs = pstmt.executeQuery();
							rs.next();
							if(rs.getFloat(1)+totalTime <= 8.0)
							{
								availableDates.add(date_values);
								time_slot.add(rs.getFloat(1));
							}
						}
						
					}catch(SQLException e){
						e.printStackTrace();
					}
					
				}
				System.out.println("Select a Date");
				System.out.println("1. "+availableDates.get(0));
				System.out.println("2. "+availableDates.get(1));
				System.out.println("3. Go Back");
				boolean exit = false;
				while(!exit) {
					
					String input = reader.nextLine();
					if (input.startsWith("1")) {
						//Update Service Table
						
						formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(0));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(0);
						calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt.setString(1, "SR"+(service_count+1));
							service_count += 1;
							pstmt.setString(2, emp_id);
							pstmt.setString(3, this.c_id);
							pstmt.setString(4, this.sc_id);
							pstmt.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//							 System.out.println("   "+timestamp);
							pstmt.setTimestamp(6, timestamp);
							pstmt.setString(7, null);
							pstmt.setString(8, service_type);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt.setTimestamp(9, timestamp);
							pstmt.setFloat(10, totalTime);
							pstmt.setFloat(11, totalCost);
							pstmt.executeQuery();
							if(pstmt.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(0) == 0) {
				        	//INSERT
				        	try{
								pstmt = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt.setString(1, emp_id);
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(2, timestamp);
								pstmt.setFloat(3, totalTime);
								pstmt.executeQuery();
								if(pstmt.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id);
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
//								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt.setFloat(1, quantity.get(z));
								pstmt.setString(2, this.sc_id);
								pstmt.setString(3, partID.get(z));
								pstmt.executeQuery();
								
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
				        
		    		} else if(input.startsWith("2")) {
		    			formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
						formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						formatter_3 = new SimpleDateFormat("dd-MMM-yy");
						calendar  = Calendar.getInstance();
						calendar.setTime(availableDates.get(1));
						calendar.add(Calendar.HOUR, -2);
						float timeDiff = time_slot.get(1);
						calendar.add(Calendar.HOUR, (int)timeDiff);
				        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
				        String start_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String start_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, -1);
				        calendar.add(Calendar.HOUR, (int)totalTime);
				        calendar.add(Calendar.MINUTE, (int) ((totalTime - (int)totalTime)*60));
				        String end_time_service = formatter_1.format(calendar.getTime()).toString();
				        calendar.add(Calendar.DATE, 1);
				        String end_time_service1 = formatter_1.format(calendar.getTime()).toString();
				        
				        System.out.println(start_time_service+" "+end_time_service);
				        
				        try{
							pstmt = this.conn.prepareStatement("INSERT INTO SERVICE "
									+ "(SER_ID, E_ID, C_ID, SC_ID, LICENSE_NO, END_TIME, BASIC_FID, MAINTENANCE_TYPE, START_DATE, LABORTIME, TOTALCOST) "
									+ "VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
							pstmt.setString(1, "SR"+(service_count+1));
							service_count += 1;
							pstmt.setString(2, emp_id);
							pstmt.setString(3, this.c_id);
							pstmt.setString(4, this.sc_id);
							pstmt.setString(5, this.vehicle_license);
							 java.util.Date parsedDate = formatter_1.parse(end_time_service1);
							 
							 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
							 System.out.println("   "+timestamp);
							pstmt.setTimestamp(6, timestamp);
							pstmt.setString(7, null);
							pstmt.setString(8, service_type);
							parsedDate = formatter_1.parse(start_time_service1);
							timestamp = new java.sql.Timestamp(parsedDate.getTime());
							pstmt.setTimestamp(9, timestamp);
							pstmt.setFloat(10, totalTime);
							pstmt.setFloat(11, totalCost);
							pstmt.executeQuery();
							if(pstmt.executeUpdate() == 0)
								System.out.println("Service Create Failed");
							else
								System.out.println("Service create success");
						}catch(Exception e){
//							e.printStackTrace();
						}
				        
		    			//Update EMployee table
				        if(time_slot.get(0) == 0) {
				        	//INSERT
				        	try{
								pstmt = this.conn.prepareStatement("INSERT INTO MECHANIC "
										+ "(E_ID, WORKD_DATE, HOURS) "
										+ "VALUES "
										+ "(?, ?, ?)");
								pstmt.setString(1, emp_id);
								Date parsedDate = formatter_3.parse(end_time_service1);
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(2, timestamp);
								pstmt.setFloat(3, totalTime);
								pstmt.executeQuery();
								
							}catch(Exception e){
//								e.printStackTrace();
							}
				        }
				        else {
				        	try{
				        		PreparedStatement pstmt3 = null;
								
								pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
								pstmt3.setFloat(1, totalTime);
								pstmt3.setString(2, emp_id);
								java.util.Date date_temp_s = formatter_1.parse(end_time_service1);
								java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
//								System.out.println(sqlStartDate_temp_s);
								pstmt3.setDate(3, sqlStartDate_temp_s);
								pstmt3.executeQuery();
								if(pstmt3.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
								e.printStackTrace();
							}
				        }
				        
		    			
		    			//Update Parts table
						for(int z = 0; z < partID.size(); z++) {
							try{
//								System.out.println(" "+quantity.get(z)+" "+sc_id+" "+partID.get(z));
								pstmt = this.conn.prepareStatement("UPDATE INVENTORY SET QUANTITY = QUANTITY - ? WHERE SC_ID = ? and P_ID = ?");
								pstmt.setFloat(1, quantity.get(z));
								pstmt.setString(2, this.sc_id);
								pstmt.setString(3, partID.get(z));
								pstmt.executeQuery();
							
							}catch(Exception e){
//								e.printStackTrace();
							}
						}
		    		} else if(input.startsWith("3")) {
		    			exit = true;
		    		}
					System.out.println("3.  Go Back");
				}
				
			}
		}
		else {
			System.out.println("Parts missing, Order for part will be placed. Please check again after");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 3);
			System.out.println(dateFormat.format(cal.getTime()));
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
		if(type.equals("C"))
		{
			try{
				pstmt = conn.prepareStatement("SELECT BASIC_TASKID from Maintenance where make = ? and model = ? and type = ?");
				pstmt.setString(1, make);
				pstmt.setString(2, model);
				pstmt.setString(3, "C");
				rs = pstmt.executeQuery();
				while(rs.next())  {
					basicTasks.add(rs.getString(1));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(type.equals("C") || type.equals("B"))
		{
			try{
				pstmt = conn.prepareStatement("SELECT BASIC_TASKID from Maintenance where make = ? and model = ? and type = ?");
				pstmt.setString(1, make);
				pstmt.setString(2, model);
				pstmt.setString(3, "B");
				rs = pstmt.executeQuery();
				while(rs.next())  {
					basicTasks.add(rs.getString(1));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(type.equals("C") || type.equals("B") || type.equals("A"))
		{
			try{
				pstmt = conn.prepareStatement("SELECT BASIC_TASKID from Maintenance where make = ? and model = ? and type = ?");
				pstmt.setString(1, make);
				pstmt.setString(2, model);
				pstmt.setString(3, "A");
				rs = pstmt.executeQuery();
				while(rs.next())  {
					basicTasks.add(rs.getString(1));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
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
			pstmt = conn.prepareStatement("SELECT time_required, p_id, part_quantity, Charge_rate from TASKS where make = ? and model = ? and basic_taskid = ?");
			pstmt.setString(1, make);
			pstmt.setString(2, model);
			pstmt.setString(3, taskid);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				details.add(rs.getString(1));
				details.add(rs.getString(2));
				details.add(rs.getString(3));
				details.add(rs.getString(4));
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
	
	public void rescheduleService() {
		
	}
	
}