package db_package;

import java.sql.Connection;
import java.util.Scanner;

public class ServicePage {
	Scanner reader;
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
			System.out.println(" 3. Reschedule Service");
			System.out.println(" 4. Go Back");
			
			String input= reader.nextLine();
		
			if (input.equals("1")) {
				printServiceHistory(Integer.parseInt(cus.c_id));
			} else if (input.equals("2")) {
				
				System.out.println(" Enter  License Plate");
				String licensePlate= reader.nextLine();
				System.out.println(" Enter  CurrentMileage");
				String currentMileage = reader.nextLine();
				System.out.println("Enter  MechanicName");
				String mechanicName = reader.nextLine();
				
				System.out.println("### Select option ###");
				System.out.println(" 1. Schedule Maintenance");
				System.out.println(" 2. Schedule Repair");
				System.out.println(" 3. Go Back");
				
				input= reader.nextLine();
				if (input.equals("1")) {
					scheduleMaintenanceService(Integer.parseInt(cus.c_id), licensePlate, currentMileage ,  mechanicName);
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
	
	Boolean receptionistServicePage() {
		Boolean goback = false;
		if (used_by != Role.RECEPTIONIST) {
			System.out.println("Only a Receptionist can Access this page");
			return false;
		}
		
		while(!goback) {
			System.out.println("### Receptionist Service page ###");
			System.out.println("### Select option ###");
			System.out.println(" 1.  View Service History");
			System.out.println(" 2.  Schedule Service");
			System.out.println(" 3. Reschedule Service");
			System.out.println(" 4. Go Back");
			
			
		}
		
		
		return true;
	}
	
	Boolean managerServicePage() {
		if (used_by != Role.MANAGER) {
			System.out.println("Only a Manager can Access this page");
			return false;
		}
		
		return true;
	}
	
	private void printServiceHistory(int customer_id) {
		
	}
	
	private void printServiceHistory(int customer_id, int license_plate) {
		
	}
	
	
	private void printServiceHistoryByServiceCenter(int serviceC_id) {
		
	}
	
	void scheduleMaintenanceService(int c_id, String licensePlate, String currentMileage , String mechanicName) {
	
	}
	
	void scheduleRepairService(int c_id ,String licensePlate, String currentMileage , String mechanicName) {
		Repair repairService = new Repair(this.sc_id, this.conn);
		
	}
	
	void rescheduleService(int c_id) {
		
	}
	
	
}
