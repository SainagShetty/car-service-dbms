package db_package;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
enum OrderStatus{
	NEW, PENDING, COMPLETED;
}
class Order {
	// only manager can create this class 
	Connection conn;
	
	String orderID = null;
	Date orderDate;
	Timestamp expectedDate;
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
		
		System.out.println("Here after actual");
		dbCreateOrder();
		
		if (this.orderId != null) {
			System.out.println("Order Created. Order ID :"+ this.orderId);
			//print estimated date TODO
		}
	}
	
	void dbCreateOrder(){
		iterable+=1;
		//set OrderId here
		PreparedStatement p = null;
		int r;
		try {
			p = this.conn.prepareStatement("INSERT INTO ORDERS(ORDER_PLACEMENT_DATE,EXPECTED_DELIVERY_DATE,ACTUAL_DELIVERY_DATE,O_ID,ORIGIN_D_ID,ORIGIN_SC_ID,P_ID,DESTINATION_SC_ID,QUANTITY,O_STATE) VALUES (?,?,?,?,?,?,?,?,?,?)");
			p.setDate(1,this.orderDate);
			p.setTimestamp(2,this.expectedDate);
			p.setDate(3,this.ActualDelivery);
			p.setString(4,Integer.toString(iterable));
			p.setString(5,this.orginDID);
			p.setString(6,this.orginScID);
			p.setString(7,this.partID);
			p.setString(8,this.DestSCID);
			p.setString(9,this.Quantity);
			p.setString(10,this.status);
			r=p.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	void markOrderComplete() {
		this.status = OrderStatus.COMPLETED.toString();
		this.ActualDelivery = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}
	
	
	private void setOrigin(String req_part, String req_quantity){
		Calendar calendar;
		System.out.println("In set origin");
		SimpleDateFormat formatter_3 = new SimpleDateFormat("dd-MMM-yy");
		calendar  = Calendar.getInstance();
		
		 for( String scid : DbApplication.ServiceCIDList) {
			 ServiceCenter sc_instance = new ServiceCenter(scid, this.conn);
			 if(sc_instance.partAvailableForDelivery(req_part, req_quantity)) {
				 this.partID = req_part;
				 this.Quantity = req_quantity;
				  this.orginScID = scid;
				  this.orginDID = null;
				  java.util.Date parsedDate = null;
				  calendar.add(Calendar.DATE, 1);
				  SimpleDateFormat formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
				  String expected_Date = formatter_1.format(calendar.getTime()).toString();
				  try {
					parsedDate = formatter_1.parse(expected_Date);
					Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
					System.out.println("   "+timestamp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  //expectedDate = new java.sql.Date(parsedDate.getTime());
				  System.out.println("Expected Date Sc" +expectedDate.toString() );
				  origin_found = true;
				  break;
			 }
		 }
		 
		 // find distributor 
		 if (!origin_found) {
			 String[] distributorId = new String[2] ;
			 
			 
			 	distributorId = Part.findDistributor(req_part, req_quantity, conn);
			 	System.out.println("here again");
				 this.partID = req_part;
				 this.Quantity = req_quantity;
				 this.orginScID = null;
				 this.orginDID = distributorId[0];
				 java.util.Date parsedDate = null;
				 try {
				 calendar.add(Calendar.DATE, Integer.parseInt(distributorId[1]));
				  SimpleDateFormat formatter_1 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
				  String expected_Date = formatter_1.format(calendar.getTime()).toString();
				  
				 
				 
				 System.out.println("here again2");
			
				 System.out.println("here again2");
				 try {
						parsedDate = formatter_1.parse(expected_Date);
						Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
						System.out.println("   "+timestamp);
						this.expectedDate = timestamp;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  //expectedDate = new java.sql.Date(parsedDate.getTime());
					  System.out.println("Expected Date did " +expectedDate.toString() );
				 
				 System.out.println(expectedDate);			 
				 } catch ( Exception e) {
					 e.printStackTrace();
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
			PreparedStatement pstmt2 = conn.prepareStatement("select UNIT_PRICE from PARTS where P_ID=?");
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString("ORIGIN_D_ID") != null){
					supplier=rs.getString("ORIGIN_D_ID");
				}
				else{
					supplier=rs.getString("ORIGIN_SC_ID");
				}
				pstmt2.setString(1,rs.getString("P_ID"));
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
			PreparedStatement pstmt2 = conn.prepareStatement("select UNIT_PRICE from PARTS where P_ID=?");
			pstmt.setString(1,orderid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String sup_dis = rs.getString("ORIGIN_D_ID") ;
				
				if(sup_dis != null){
					supplier=rs.getString("ORIGIN_D_ID");
				}
				else{
					supplier=rs.getString("ORIGIN_SC_ID");
				}
				pstmt2.setString(1,rs.getString("P_ID"));
				r2 = pstmt2.executeQuery();
				r2.next();
				unitprice = Float.parseFloat(r2.getString(1));
				//Timestamp expecteddate = r2.getTimestamp(2);
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
	
	static void updateOrderlist(String orderids[], Connection conn) {
	        int r;
		 for(String order : orderids) {
			 PreparedStatement pstmt = null;
			 try {
				pstmt = conn.prepareStatement("update ORDERS set O_STATE=\""+OrderStatus.COMPLETED.toString()+"\"where O_ID=?");
				pstmt.setString(1,order);
				 r=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
	}
	
	
}
