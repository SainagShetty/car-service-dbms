package db_package;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ServicePage {
	Scanner reader = new Scanner(System.in);
	Connection conn;
	int used_by;
	
	String sc_id = "1"; //TODO
	
	ServicePage(int role, Connection conn){	
		this.used_by = role;
		this.conn = conn;
	}
	
	Boolean customerServicePage(Customer cus) {
		
		Boolean goback = false;
		if (used_by != Role.CUSTOMER) {
			System.out.println("Only a Customer can Access this page");
			return false;
		}
		
		
		while(!goback) {
			System.out.println("### Customer Service page ###");
			System.out.println("### Select option ###");
			System.out.println(" 1.  View Service History");
			System.out.println(" 2.  Schedule Service");
			System.out.println(" 3.  Reschedule Service");
			System.out.println(" 4.  Go Back");
			
			String input= reader.nextLine().trim();
		
			if (input.equals("1")) {
				printServiceHistory(cus.c_id);
			} else if (input.equals("2")) {
				
				String mechanicName = null;
				System.out.println(" Enter  License Plate");
				String licensePlate= reader.nextLine().trim();
				System.out.println(" Enter  CurrentMileage");
				String currentMileage = reader.nextLine().trim();
				System.out.println("Enter  MechanicName");
				mechanicName = reader.nextLine().trim();
				
				System.out.println("### Select option ###");
				System.out.println(" 1. Schedule Maintenance");
				System.out.println(" 2. Schedule Repair");
				System.out.println(" 3. Go Back");
				
				input= reader.nextLine().trim();
				if (input.equals("1")) {
					scheduleMaintenanceService(cus.emailID, licensePlate, currentMileage ,  mechanicName);
				} else if (input.equals("2")) {
					scheduleRepairService(cus.emailID, licensePlate, currentMileage ,  mechanicName);
				} else if (input.equals("3")) {
					continue;
				}
			} else if (input.equals("3")) {
				rescheduleService(Integer.parseInt(cus.c_id));
				
			} else if (input.equals("4")) {
				goback = true;
				break;
				
			} else {
				System.out.println("Incorrect input");
				continue;
			}
		}
		
		if (!goback) {
			// 
		}
		return true;
	}
	
	void receptionistScheduleServicePage(String sc_id) {
		this.sc_id = sc_id;
		Boolean goback = false;
		if (used_by != Role.RECEPTIONIST) {
			System.out.println("Only a Receptionist can Access this page");
			return;
		}
		
		while(!goback) {
			System.out.println("### ScheduleService###");
			System.out.println("### Select option ###");
			System.out.println(" 1.  Schedule Maintenance");
			System.out.println(" 2.  Schedule Repair");
			System.out.println(" 3.  Go Back");
			
			String input = reader.nextLine().trim();
			
			if (input.startsWith("1")) {
				//TODO
				System.out.println("Enter Customer Email id");
				String temp_email = reader.nextLine();
				System.out.println("License Plate");
				String temp_license = reader.nextLine();
				System.out.println("Current Milage");
				float temp_milage = Float.parseFloat(reader.nextLine());
				System.out.println("Mechanic Name");
				String temp_ename = reader.nextLine();
				Maintenance maintenance = new Maintenance(this.sc_id, temp_email, temp_license, temp_milage, temp_ename, this.conn);
			} else if (input.startsWith("2")){
				System.out.println("Enter Customer Email id");
				String temp_email = reader.nextLine();
				System.out.println("License Plate");
				String temp_license = reader.nextLine();
				System.out.println("Current Milage");
				float temp_milage = Float.parseFloat(reader.nextLine());
				System.out.println("Mechanic Name");
				String temp_ename = reader.nextLine();
				Repair repair = new Repair(this.sc_id, temp_email, temp_license, temp_milage, temp_ename, this.conn);
				//TODO
				System.out.println("Enter Customer Email id");
				String temp_email = reader.nextLine();
				System.out.println("License Plate");
				String temp_license = reader.nextLine();
				System.out.println("Current Milage");
				float temp_milage = Float.parseFloat(reader.nextLine());
				System.out.println("Mechanic Name");
				String temp_ename = reader.nextLine();
				Repair repair = new Repair(this.sc_id, temp_email, temp_license, temp_milage, temp_ename, this.conn);
			} else if (input.startsWith("3")){
				goback =  true;
			} else {
				System.out.println("Invalid input. Try Again");
			}
		}	
	}
	
	
	Boolean managerServicePage() {
		if (used_by != Role.MANAGER) {
			System.out.println("Only a Manager can Access this page");
			return false;
		}
		
		return true;
	}
	
	private void printServiceHistory(String c_id) {
		boolean status = false;
	 	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM Service WHERE c_id=?");
			pstmt.setString(1, c_id);
			rs = pstmt.executeQuery();

		while(rs.next())  {
						
						System.out.println("Service History for Service Center");
						String service_id = rs.getString(1);
						String employee_id = rs.getString(2);
						String cid = rs.getString(3);
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
	}
	
	private void printServiceHistory(String customer_id, String license_plate) {
		
	}
	
	
	private void printServiceHistoryByServiceCenter(String serviceC_id) {
		
	}
	
	void scheduleMaintenanceService(String c_email, String licensePlate, String currentMileage , String mechanicName) {
		String temp_license = reader.nextLine();
		float temp_milage = Float.parseFloat(currentMileage);
		String temp_ename = mechanicName;
		Maintenance maintenance = new Maintenance(this.sc_id, c_email, temp_license, temp_milage, temp_ename, this.conn);
	}
	
	void scheduleRepairService(String emailID ,String licensePlate, String currentMileage , String mechanicName) {
		float temp_milage = Float.parseFloat(currentMileage);
		Repair repairService = new Repair(this.sc_id, emailID, licensePlate, temp_milage, mechanicName, this.conn);
		
	}
	
	void rescheduleService(int c_id) {
		
	}
	
	void receptionistRescheduleServicePage() {
		List<String> serviceIDlist = null;
		Boolean goback = false;
		if (used_by != Role.RECEPTIONIST) {
			System.out.println("Only a Receptionist can Access this page");
			return;
		}
		System.out.println("### RescheduleService###");
		System.out.println("Enter customer email address");
		String cus_email = reader.nextLine().trim();
		while(!goback) {
			
//			if (serviceIDlist == null)
//				serviceIDlist = printServicesForCustomer(cus_email);
			
			ArrayList<String> serviceID = printServicesForCustomer(cus_email);
			System.out.println("### Select option ###");
			System.out.println(" 1.  Pick a service");
			System.out.println(" 2.  Go Back");
			
			String input = reader.nextLine().trim();
			
				if (input.equals("1")) {
				
					boolean valid = false;
					System.out.println("Enter Service ID");
					String sc_id_val = reader.nextLine().trim();
	//				for( String sc : serviceIDlist ) {
	//					if (sc.equalsIgnoreCase(sc_id_val)) {
	//						rescheduleServiceForCus(cus_email, sc_id_val);
	//						valid = true;
	//						break;
	//					} 
	//				}
					rescheduleServiceForCus(cus_email, sc_id_val);
					
					//if (!valid)
						//System.out.println("Service id not found");
					
				} else if (input.equals("2")) {
					goback = true;
					
				} else if (input.equals("3")) {
					continue;
				}
			
		}
	}
		
	private void rescheduleServiceForCus(String cus_email, String sc_id_val) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		String e_id = null;
		Date start_date = null;
		float labortime = 0;
		Calendar calendar;
		SimpleDateFormat formatter_1;
		SimpleDateFormat formatter_2;
		SimpleDateFormat formatter_3;
//		Display the two identified service dates and mechanic name, followed by the menu.
		
		while(true) {
		System.out.println("### Select option ###");
		System.out.println(" 1.  Reschedule Date");
		System.out.println(" 2.  Go Back");
		
		String input = reader.nextLine().trim();
		
		
			if(input.equals("1")){
				System.out.println("### Pick two dates ###");
				PreparedStatement pstmt = null;
		 		ResultSet rs;

		 		try{
					pstmt = conn.prepareStatement("SELECT e_id, start_date, lobortime from SERVICE where SER_ID=?");
					pstmt.setString(1, sc_id_val);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						e_id = rs.getString(1);
						start_date = rs.getDate(2);
						labortime = rs.getFloat(3);
					}
					
					ArrayList<java.sql.Date> currentWeekMap = new ArrayList<java.sql.Date>();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate today = LocalDate.now();
					int count = 0;
					for (int i=1; count < 7; i++) {
					    LocalDate newDay = today.plusDays(i);
					    String dayOfWeek = newDay.getDayOfWeek().toString();
//					    System.out.println("Day of the week" +dayOfWeek);
					    if(dayOfWeek.equals("SATURDAY") || dayOfWeek.equals("SUNDAY")) {
					    	continue;
					    }
					    String formattedDate = newDay.format(formatter);
					    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						  java.util.Date date = null;
						try {
							date = dateFormat.parse(formattedDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//						  System.out.println(sqlStartDate);
					    
						  if(sqlStartDate.compareTo(start_date) == 1) {
							  currentWeekMap.add(sqlStartDate);
							  count++;
						  }
					}
					
					ArrayList<java.sql.Date> availableDates = new ArrayList<java.sql.Date>();
					ArrayList<Float> time_slot = new ArrayList<Float>();
					for(java.sql.Date date_values: currentWeekMap) {
						try{
							pstmt = conn.prepareStatement("SELECT COUNT(*) from MECHANIC where e_id = ? and WORKD_DATE = ?");
							pstmt.setString(1, e_id);
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
								pstmt.setString(1, e_id);
								pstmt.setDate(2, date_values);
								rs = pstmt.executeQuery();
								rs.next();
								if(rs.getFloat(1)+labortime <= 8.0)
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
						
						input = reader.nextLine();
						if (input.startsWith("1")) {
							//Update Service Table
							
							formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
							formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
							formatter_3 = new SimpleDateFormat("dd-MMM-yy");
							calendar  = Calendar.getInstance();
							calendar.setTime(availableDates.get(0));
							calendar.add(Calendar.HOUR, -2);
							float timeDiff = time_slot.get(0);
							System.out.println(timeDiff);
					        calendar.add(Calendar.HOUR, (int)timeDiff);
					        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
					        String start_time_service = formatter_1.format(calendar.getTime()).toString();
					        calendar.add(Calendar.HOUR, (int)labortime);
					        calendar.add(Calendar.MINUTE, (int) ((labortime - (int)labortime)*60));
					        String end_time_service = formatter_1.format(calendar.getTime()).toString();
					        
//					        System.out.println(start_time_service+" "+end_time_service);
					        
					        try{
								pstmt = this.conn.prepareStatement("UPDATE SERVICE SET END_TIME = ? and START_TIME = ? WHERE SER_ID = ?");
								java.util.Date parsedDate = formatter_1.parse(end_time_service);
								 
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(1, timestamp);
								parsedDate = formatter_1.parse(start_time_service);
								timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(2, timestamp);
								pstmt.setString(3, sc_id_val);
								pstmt.executeQuery();
								if(pstmt.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
					        
			    			//Update EMployee table
					        if(time_slot.get(0) == 0) {
					        	//INSERT
					        	try{
									pstmt = this.conn.prepareStatement("INSERT INTO MECHANIC "
											+ "(E_ID, WORKD_DATE, HOURS) "
											+ "VALUES "
											+ "(?, ?, ?)");
									pstmt.setString(1, e_id);
									Date parsedDate = formatter_3.parse(end_time_service);
									 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
									pstmt.setTimestamp(2, timestamp);
									pstmt.setFloat(3, labortime);
									pstmt.executeQuery();
									if(pstmt.executeUpdate() == 0)
										System.out.println("Service Create Failed");
									else
										System.out.println("Service create success");
								}catch(Exception e){
//									e.printStackTrace();
								}
					        }
					        else {
					        	try{
					        		PreparedStatement pstmt3 = null;
									
									pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
									pstmt3.setFloat(1, labortime);
									pstmt3.setString(2, e_id);
									java.util.Date date_temp_s = formatter_1.parse(end_time_service);
									java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
//									System.out.println(sqlStartDate_temp_s);
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
					        
			    			
			    			
			    		} else if(input.startsWith("2")) {
			    			formatter_2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
							formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
							formatter_3 = new SimpleDateFormat("dd-MMM-yy");
							calendar  = Calendar.getInstance();
							calendar.setTime(availableDates.get(1));
							calendar.add(Calendar.HOUR, -2);
							float timeDiff = time_slot.get(1);
							System.out.println(timeDiff);
					        calendar.add(Calendar.HOUR, (int)timeDiff);
					        calendar.add(Calendar.MINUTE, (int) ((timeDiff - (int)timeDiff)*60));
					        String start_time_service = formatter_1.format(calendar.getTime()).toString();
					        calendar.add(Calendar.HOUR, (int)labortime);
					        calendar.add(Calendar.MINUTE, (int) ((labortime - (int)labortime)*60));
					        String end_time_service = formatter_1.format(calendar.getTime()).toString();
					        
//					        System.out.println(start_time_service+" "+end_time_service);
					        
					        try{
								pstmt = this.conn.prepareStatement("UPDATE SERVICE SET END_TIME = ? and START_TIME = ? WHERE SER_ID = ?");
								java.util.Date parsedDate = formatter_1.parse(end_time_service);
								 
								 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(1, timestamp);
								parsedDate = formatter_1.parse(start_time_service);
								timestamp = new java.sql.Timestamp(parsedDate.getTime());
								pstmt.setTimestamp(2, timestamp);
								pstmt.setString(3, sc_id_val);
								pstmt.executeQuery();
								if(pstmt.executeUpdate() == 0)
									System.out.println("Service Create Failed");
								else
									System.out.println("Service create success");
							}catch(Exception e){
//								e.printStackTrace();
							}
					        
			    			//Update EMployee table
					        if(time_slot.get(0) == 0) {
					        	//INSERT
					        	try{
									pstmt = this.conn.prepareStatement("INSERT INTO MECHANIC "
											+ "(E_ID, WORKD_DATE, HOURS) "
											+ "VALUES "
											+ "(?, ?, ?)");
									pstmt.setString(1, e_id);
									Date parsedDate = formatter_3.parse(end_time_service);
									 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
									pstmt.setTimestamp(2, timestamp);
									pstmt.setFloat(3, labortime);
									pstmt.executeQuery();
									
								}catch(Exception e){
//									e.printStackTrace();
								}
					        }
					        else {
					        	try{
					        		PreparedStatement pstmt3 = null;
									
									pstmt3 = this.conn.prepareStatement("UPDATE MECHANIC SET HOURS = HOURS + ? WHERE E_ID = ? and WORKD_DATE = ?");
									pstmt3.setFloat(1, labortime);
									pstmt3.setString(2, e_id);
									java.util.Date date_temp_s = formatter_1.parse(end_time_service);
									java.sql.Date sqlStartDate_temp_s = new java.sql.Date(date_temp_s.getTime());
//									System.out.println(sqlStartDate_temp_s);
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
					        			    			
			    		} else if(input.startsWith("3")) {
			    			exit = true;
			    		}
						System.out.println("3.  Go Back");
					}
					
	            }
	            catch(SQLException e){
					e.printStackTrace();
				}
				
			} else if (input.equals("2")) {
				break;
			} else {
				continue;
			}
		}
	}

//	List<String> printServicesForCustomer(String Cus_email) {
	public ArrayList<String> printServicesForCustomer(String Cus_email) {
//		Display the following details for all upcoming services for this customer, followed by the menu
//		A. LicensePlate
//		B. ServiceID
//		C. ServiceDate
//		D. ServiceType
//		(Maintenance/Re
//		pair)
//		E. ServiceDetails
//		(Service A/B/C or Problem)
		ArrayList<String> serviceID = Service.serviceHistory(Cus_email, true, conn);
		return serviceID;
		//List<String> serviceIDlist = new ArrayList<String>();	
		//return serviceIDlist;
	}

	
}
