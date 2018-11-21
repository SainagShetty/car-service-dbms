package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	static void notificationPage(String sc_id, Connection conn) {
		printallNotifications(sc_id, conn);	
	}
	
	static void printallNotifications(String sc_id, Connection conn) {
		Scanner reader = new Scanner(System.in);
		System.out.println( "###Notifications ####");
		
		/////TODO get from notifications table .. use delayed_by
		
		printNotificationsFromTable(sc_id, conn);
		
		
		
	
		while(true) {
			System.out.println("Select 1 for Details.  2 to go back");
			String input= reader.nextLine().trim();
			if (input.startsWith("1")) {
				System.out.println("Enter order id");
				String id = reader.nextLine().trim();
				Order.printOrderById(id, conn);
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
	
	
	static void printNotificationsFromTable(String Sc_id,Connection conn){
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("SELECT N_ID,  NOTIF_DATE, O_ID FROM NOTIFICATION where SC_ID=?");
			pstmt.setString(1, Sc_id);
			
			rs = pstmt.executeQuery(); //All notifications for service Center
			
			while(rs.next())  {
				String supplier;
				PreparedStatement pstmt2 = conn.prepareStatement("select * from orders where O_ID=?");
				String oid = rs.getString(3);
				pstmt2.setString(1, oid);
				
				ResultSet r2= null;
				r2 = pstmt2.executeQuery(); // Order for order id
				if (r2 == null) {
					System.out.println("nul");
				}
				while(r2.next()) {
					
					String sup_dis = r2.getString(5) ;
					
					if(sup_dis!= null){
						supplier=sup_dis;
					}
					else{
						supplier=r2.getString(6);
					}
					
					Timestamp expecteddate = r2.getTimestamp(2);
					try {
						Calendar calendar  = Calendar.getInstance();
						SimpleDateFormat formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
						String currentDate = formatter_1.format(calendar.getTime()).toString();
						java.util.Date parsedDate = null;
						parsedDate = formatter_1.parse(currentDate);
						Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
						System.out.println("Current date" + timestamp.toString() );
						
						if(timestamp.after(expecteddate)) {
							long diff = timestamp.getTime() - expecteddate.getTime();
							long delayed_days = diff / (24 * 60 * 60 * 1000);
							
							System.out.println("Notif_id "+ rs.getString(1) + " NotificationDate/Time" + rs.getDate(2)  + " Order ID- " + oid + " Expected- " +  expecteddate.toString() + " SupplierName- "+ supplier + " Delayed by"+ delayed_days );
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
//				ResultSet r3= null;
//				PreparedStatement pstmt3 = conn.prepareStatement("select UNIT_PRICE from PARTS where P_ID=?"); //Part unitprice
//				pstmt3.setString(1,rs.getString("P_ID"));
//				r3 = pstmt2.executeQuery();
//				r3.next();
//				
//				unitprice = Float.parseFloat(r3.getString(1));
				
				
				
				//quantity = Integer.parseInt(r2.getString("QUANTITY"));
				//tp = unitprice*quantity;
				
				
				
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
}
