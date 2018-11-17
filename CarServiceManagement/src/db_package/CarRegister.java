package db_package;

import java.util.Scanner;
import java.sql.Connection;
class CarRegister {
	Scanner reader;
	Connection conn;
	int created_by_role;
	
	CarRegister(int role, Connection conn){
		this.created_by_role = role;
		this.conn = conn;
	}
	
	void registerCar(int c_id) {
		
		System.out.println("### RegisterCar page ###");
		System.out.println(" Enter  License Plate");
		String licensePlate= reader.nextLine();
		System.out.println(" Enter  Purchasedate");
		String Purchasedate = reader.nextLine();
		System.out.println("Enter  Make");
		String make = reader.nextLine();
		System.out.println("Enter Model");
		String model = reader.nextLine();
		System.out.println("Enter Year");
		String year = reader.nextLine();
		System.out.println("Enter Current mileage");
		String curMileage = reader.nextLine();
		System.out.println("Enter Last Service Date");
		String lastServiceDate = reader.nextLine();
		
		System.out.println("### Select option ###");
		System.out.println(" 1.  Register");
		System.out.println(" 2.  Cancel");
		String input= reader.nextLine();
		
		if(input.equals("1")) {
			Vehicle new_vehicle = new Vehicle(model,make, licensePlate,Purchasedate, Integer.parseInt(year), conn);
			new_vehicle.setCustomer(c_id);
		} else if (input.equals("2")) {
			return;
		}
	}
}
