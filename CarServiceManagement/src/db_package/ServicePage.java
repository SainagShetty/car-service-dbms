package db_package;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
					scheduleRepairService(Integer.parseInt(cus.c_id), licensePlate, currentMileage ,  mechanicName);
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
				//TODO
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
	
	void scheduleRepairService(int c_id ,String licensePlate, String currentMileage , String mechanicName) {
		Repair repairService = new Repair(this.sc_id, this.conn);
		
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
			
			printServicesForCustomer(cus_email);
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
		
		
//		Display the two identified service dates and mechanic name, followed by the menu.
		
		while(true) {
		System.out.println("### Select option ###");
		System.out.println(" 1.  Reschedule Date");
		System.out.println(" 2.  Go Back");
		
		String input = reader.nextLine().trim();
		
		
			if(input.equals("1")){
				System.out.println("### Pick two dates ###");
				//TODO
			} else if (input.equals("2")) {
				break;
			} else {
				continue;
			}
		}
	}

//	List<String> printServicesForCustomer(String Cus_email) {
	void printServicesForCustomer(String Cus_email) {
//		Display the following details for all upcoming services for this customer, followed by the menu
//		A. LicensePlate
//		B. ServiceID
//		C. ServiceDate
//		D. ServiceType
//		(Maintenance/Re
//		pair)
//		E. ServiceDetails
//		(Service A/B/C or Problem)
		
		Service.serviceHistory(Cus_email, conn);
		//List<String> serviceIDlist = new ArrayList<String>();	
		//return serviceIDlist;
	}

	
}
