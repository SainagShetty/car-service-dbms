package db_package;
import java.util.Date;
import java.sql.*;

class Part {
	Connection conn;
	String p_id;
	String p_name;
	String unit_price;
	String warranty_months;
	String make;
	
	Part(Connection conn){
		this.conn = conn;
	}
	
	
	static String[] findDistributor(String partId, String quantity) {
		String ret[] = new String[2];
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet r1=null;
		ResultSet r2=null;
		int c1=0,c2=0;
		try {
			pstmt1 = con.prepareStatement("select D1 from PARTS where P_ID=?");
		
		
		
			pstmt1.setString(1,partId);
		
		
			pstmt2 = con.prepareStatement("select D2 from PARTS where P_ID=?");
		
		
		     pstmt2.setString(1,partId);
		     r1=pstmt1.executeQuery();
		     r2=pstmt2.executeQuery();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		if(r1!=null) {
		try {
			c1=r1.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
		c1=-99999;
		}
		if(r2!=null) {
		try {
			c2=r2.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
		c2=-99999;
		}
		if(c1 == -99999) {
			ret[0]="D0002";
			ret[1]=Integer.toString(c2);
			return ret;
		}
		else if(c2 == -99999 ) {
			ret[0]="D0001";
			ret[1]=Integer.toString(c1);
			return ret;
		}
		
		
		//TODO 
		// check the distributor for part id. return distributor id.
		
		
		return ret;
	}
	
	
	
}
