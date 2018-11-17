package src.db_package;

import java.sql.Connection;
import java.util.Scanner;

static class ServicePage {
	Scanner reader;
	Connection conn;
	int used_by;
	
	ServicePage(int role, Connection conn){	
		this.used_by = role;
		this.conn = conn;
	}
	
	Boolean customerServicePage(Customer cus) {
		
		Boolean goback = false;
		if (used_by != Role.CUSTOMER) {
			System.out.println("Only a Receptionist can Access this page");
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
				printServiceHistory(c_id);
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
					scheduleMaintenanceService(licensePlate, currentMileage ,  mechanicName);
				} else if (input.equals("2")) {
					scheduleRepairService(licensePlate, currentMileage ,  mechanicName);
				} else if (input.equals("3")) {
					continue;
				}
			} else if (input.equals("3")) {
				rescheduleService(c_id);
				
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
		if (used_by != Role.RECEPTIONIST) {
			System.out.println("Only a Receptionist can Access this page");
			return false;
		}
		
		return true;
	}
	
	Boolean managerServicePage() {
		if (used_by != Role.MANAGER) {
			System.out.println("Only a Receptionist can Access this page");
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
	
	void scheduleMaintenanceService(String licensePlate, String currentMileage , String mechanicName) {
	
	}
	
	void scheduleRepairService(String licensePlate, String currentMileage , String mechanicName) {
		Repair repairService = new Repair(c_id, licensePlate, sc_id);
		
	}
	
	void rescheduleService(int c_id) {
		
	}
	
	
}
