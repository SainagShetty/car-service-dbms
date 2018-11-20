package db_package;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Scanner;
import java.sql.Connection;
class CarRegister {
	Scanner reader;
	Connection conn;
	int created_by_role;
	
	CarRegister(int role, Connection conn){
		reader = new Scanner(System.in);
		this.created_by_role = role;
		this.conn = conn;
	}
	
	
	Vehicle registerCar(String c_id) {
		

		System.out.println(" Enter  License Plate");
		String license_no= reader.nextLine().trim();
		System.out.println(" Enter  Purchase date(dd/MM/yyyy)");
		String date_of_purchase = reader.nextLine().trim();
		System.out.println("Enter  Make");
		String make = reader.nextLine().trim();
		System.out.println("Enter Model");
		String model = reader.nextLine().trim();
		System.out.println("Enter Year");
		String year = reader.nextLine().trim();
		System.out.println("Enter Current mileage");
		String mileage = reader.nextLine().trim();
		System.out.println("Enter Last Service Date(dd/MM/yyyy)");
		String lastServiceDate = reader.nextLine().trim();
		
		System.out.println("### Select option ###");
		System.out.println(" 1.  Register");
		System.out.println(" 2.  Cancel");
		String input= reader.nextLine().trim();
		
		if(input.equals("1")) {
			Date date_of_purchase1;
			Date last_service_date = null;
			try {
				date_of_purchase1 = new SimpleDateFormat("dd/MM/yyyy").parse(date_of_purchase);
				if(lastServiceDate.length() == 0) {
					last_service_date = null;
				}else {
					last_service_date = new SimpleDateFormat("dd/MM/yyyy").parse(lastServiceDate);
				}
				Vehicle vehicle = new Vehicle(model, make, license_no, date_of_purchase1, Integer.parseInt(year), 
						conn, c_id, Integer.parseInt(mileage), last_service_date);
				return vehicle;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (input.equals("2")) {
			return null;
		}
		return null;
	}
}
