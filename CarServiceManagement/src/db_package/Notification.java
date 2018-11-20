package db_package;

import java.sql.Date;
import java.util.Scanner;

class Notification {
	
	String notificationID;
	Date notificationDate;
	String suplierName;
	Notification(){
		
	}
//	static void addNotification(Order order){
//		//TODO	
//		
//	}
	
	String delayedby(){
		
		return new String();
	}
	
	static void notificationPage() {
		printallNotifications();	
	}
	
	static void printallNotifications() {
		Scanner reader = new Scanner(System.in);
		System.out.println( "###Notifications ####");
		
		/////TODO get from notifications table .. use delayed_by
	
		while(true) {
			System.out.println("Select 1 for Details.  2 to go back");
			String input= reader.nextLine().trim();
			if (input.startsWith("1")) {
				System.out.println("Enter order id");
				String id = reader.nextLine().trim();
				Order.printOrderById(id);
			
				while(true) {
					System.out.println("1. Go back");
					input= reader.nextLine().trim();
					if (input.startsWith("1")) {
						break;
					}	
				}
				
			} else if (input.startsWith("2")) {
				break;
			} else {
				System.out.println("Invalid input. Try Again");
			}	
		}
	}
	
	
}
