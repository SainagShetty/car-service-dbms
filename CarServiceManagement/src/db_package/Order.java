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
	static int iterable=12;
	
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
		PreparedStatement p = null;
		int r;
		p = this.conn.prepareStatement("INSERT INTO ORDERS(ORDER_PLACEMENT_DATE,EXPECTED_DELIVERY_DATE,ACTUAL_DELIVERY_DATE,O_ID,ORIGIN_D_ID,ORIGIN_SC_ID,P_ID,DESTINATION_SC_ID,QUANTITY,O_STATE) VALUES (?,?,?,?,?,?,?,?,?,?)");
		p.setDate(1,this.orderDate);
		p.setDate(2,this.expectedDate);
		p.setDate(3,this.ActualDelivery);
		p.setString(4,iterable);
		p.setString(5,this.originDID);
		p.setString(6,this.originScID);
		p.setString(7,this.partID);
		p.setString(8,this.DestSCID);
		p.setString(9,this.Quantity);
		p.setString(10,this.status);
		r=p.executeUpdate();
		iterable+=1;
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
	static void printOrderHistory(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		ResultSet r2=null;
		String supplier;
		float unitprice=0;
		int quantity;
		float tp;
		try{
			pstmt = conn.prepareStatement("select * from orders order by order_placement_date desc");
			pstmt2 = conn.prepareStatement("select UNIT_PRICE from PARTS where P_ID=?")
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString("ORIGIN_D_ID") != NULL){
					supplier=rs.getString("ORIGIN_D_ID")
				}
				else{
					supplier=rs.getString("ORIGIN_SC_ID")
				}
				pstmt2.setString(1,rs.getString("P_ID"))
				r2 = pstmt2.executeQuery();
				unitprice = Float.parseFloat(r2.getString(1));
				quantity = Integer.parseInt(rs.getString("QUANTITY"));
				tp = unitprice*quantity;


				System.out.println("Order ID- " + rs.getString("O_ID") + " Date- " + rs.getString("ORDER_PLACEMENT_DATE") + " PartName- " + rs.getString("P_ID") + " SupplierName- "+ supplier + " Quantity- "+ rs.getString("QUANTITY")+ " Unit Price- "+r2.getString(1)+" Total Price- "+Float.toString(tp));
			}
		}
	catch(SQLException e){
			e.printStackTrace();
		}
		//TODO 
		// 
		
	}
	static void printOrderById(String orderid, Connection conn) {
	       PreparedStatement pstmt = null;
		ResultSet rs=null;
		ResultSet r2=null;
		String supplier;
		float unitprice=0;
		int quantity;
		float tp;
		try{
			pstmt = conn.prepareStatement("select * from orders where O_ID=?");
			pstmt2 = conn.prepareStatement("select UNIT_PRICE from PARTS where P_ID=?")
			pstmt.setString(1,orderid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString("ORIGIN_D_ID") != NULL){
					supplier=rs.getString("ORIGIN_D_ID")
				}
				else{
					supplier=rs.getString("ORIGIN_SC_ID")
				}
				pstmt2.setString(1,rs.getString("P_ID"))
				r2 = pstmt2.executeQuery();
				unitprice = Float.parseFloat(r2.getString(1));
				quantity = Integer.parseInt(rs.getString("QUANTITY"));
				tp = unitprice*quantity;





				System.out.println("Order ID- " + rs.getString("O_ID") + " Date- " + rs.getString("ORDER_PLACEMENT_DATE") + " PartName- " + rs.getString("P_ID") + " SupplierName- "+ supplier + " Quantity- "+ rs.getString("QUANTITY")+ " Unit Price- "+r2.getString(1)+" Total Price- "+Float.toString(tp));
			}
		
		//TODO 
		// 
		
	}
	catch(SQLException e){
			e.printStackTrace();
		}
	
	        
		//TODO
		
//		A. OrderID
//		B. Date
//		C. PartName
//		D. SupplierName
//		E. PurchaserName
//		F. Quantity
//		G. Unit Price
//		H. TotalCost
//		I. Order Status
	}
	
	static void updateOrderlist(String orderids[]) {
	        int r;
		 for(String order : orderids) {
			 PreparedStatement pstmt = null;
			 pstmt = conn.prepareStatement("update ORDERS set O_STATE=\"Complete\" where O_ID=?");
			 pstmt.setString(1,order);
			 r=pstmt.executeUpdate();
		 }
	}
	
	
}
