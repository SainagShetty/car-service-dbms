package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;

enum OrderStatus{
	NEW, PENDING, COMPLETED;
}
class Order {
	// only manager can create this class 
	Connection conn;
	
	String orderID = null;
	Date orderDate;
	Date expectedDate;
	Date ActualDelivery;
	String orderId;
	String orginScID;
	String orginDID;
	String partID;
	String DestSCID;
	String Quantity;
	String status;
	
	Boolean origin_found = false;
	Order(){
	}
	
	Order(String DestSCID){
		this.DestSCID = DestSCID;
	
	}
	void placeOrder(String req_part, String req_quantity, Connection conn){
		this.conn = conn;
		
		setOrigin(req_part, req_quantity);
		Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		this.orderDate = date;
		this.status = OrderStatus.PENDING.toString();
		
		setExpectedDate(); //TODO
		this.ActualDelivery = null;
		
		dbCreateOrder();
		
		if (this.orderId != null) {
			System.out.println("Order Created. Order ID :"+ this.orderId);
			//print estimated date TODO
		}
	}
	
	void dbCreateOrder(){
		//set OrderId here
	}
	void markOrderComplete() {
		this.status = OrderStatus.COMPLETED.toString();
		this.ActualDelivery = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
	
	
	private void setOrigin(String req_part, String req_quantity){
		
		 for( String scid : DbApplication.ServiceCIDList) {
			 ServiceCenter sc_instance = new ServiceCenter(scid, this.conn);
			 if(sc_instance.partAvailableForDelivery(req_part, req_quantity)) {
				 this.partID = req_part;
				 this.Quantity = req_quantity;
				  this.orginScID = scid;
				  this.orginDID = null;	 
				  
				  origin_found = true;
				  break;
			 }
		 }
		 
		 // find distributor 
		 if (!origin_found) {
			 String distributorId;
			 
			 distributorId = Part.findDistributor(req_part, req_quantity);
			 
			 if (distributorId != null) {
				 this.partID = req_part;
				 this.Quantity = req_quantity;
				 this.orginScID = null;
				 this.orginDID = distributorId;
				 
			 }
		 }
		 
	}
	
	
	private void setExpectedDate() {
		
	}
	static void printOrderHistory(){
		
		
		//TODO 
		// 
		
	}
	
	
}
