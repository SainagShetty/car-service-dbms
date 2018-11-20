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
 	
 	
 	
	public static void addtoInventory(String part_id, String ser_id, int n, Connection conn){
 		PreparedStatement pstmt = null;
 		ResultSet q;
 		int fvalue=0;
 		int val=0;

 		try{
			pstmt = conn.prepareStatement("SELECT quantity from INVENTORY where P_ID=? and SC_ID=?");
			pstmt.setString(1, part_id);
			pstmt.setString(2, ser_id);
			q = pstmt.executeQuery();
			System.out.println(q);
			val = q.getInt(1);
			fvalue = val + n;
			pstmt = conn.prepareStatement("UPDATE INVENTORY set quantity =? where P_ID=? and SC_ID=?");
			pstmt.setString(1, Integer.toString(fvalue));
			pstmt.setString(2, part_id);
			pstmt.setString(3, ser_id);
			q = pstmt.executeQuery();
            }
            catch(SQLException e){
			e.printStackTrace();
		}


 	}
 	public static boolean subtractfromInventory(String part_id, String ser_id, int n, Connection conn){
 		PreparedStatement pstmt = null;
 		ResultSet q;
 		int fvalue=0;

 		try{
			pstmt = conn.prepareStatement("SELECT quantity from INVENTORY where P_ID=? and SC_ID=?");
			pstmt.setString(1, part_id);
			pstmt.setString(2, ser_id);
			q = pstmt.executeQuery();
			if(q.getInt(1) <n){
				return false;
			}
			else{
				fvalue =  q.getInt(1) - n;
				pstmt = conn.prepareStatement("UPDATE INVENTORY set quantity =? where P_ID=? and SC_ID=?");
				pstmt.setString(1, Integer.toString(fvalue));
				pstmt.setString(2, part_id);
				pstmt.setString(3, ser_id);
				q = pstmt.executeQuery();
				return true;
		    }
            }
            catch(SQLException e){
			e.printStackTrace();
		}

 		return false;
 	}
	
}
