package db_package;

import java.sql.*;

public class Inventory {
	Connection conn;
	int sc_id;
	int p_id;
	int quantity;	
	int threshold_quantity;
	int min_order;
	int make;

 	 Inventory(Connection conn){
 		 this.conn = conn;	 
 	 }
 	 
 	public void viewInventory(String sc_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			System.out.println("SC_ID is "+sc_id);
			pstmt = conn.prepareStatement("SELECT * FROM INVENTORY JOIN PARTS ON INVENTORY.P_ID = PARTS.P_ID WHERE sc_id=?");
			pstmt.setString(1, sc_id);
			rs = pstmt.executeQuery();
			System.out.println("Inventory for Service Center "+sc_id);
			System.out.println("------------------------------------------");
			while(rs.next())  {
				System.out.println("Part ID: "+rs.getString(3)+" Part Name: "+rs.getString(8)
						+" Quantity: "+rs.getString(4)+" Unit Price: "+rs.getString(9)
						+" Threshold Quantity: "+rs.getString(5) + " Min Order: "+rs.getString(6)
						+" Make: "+rs.getString(11));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
 	
 	
 	public static void updateInventory() {
 		//TODO by Rajat
 	}
}
