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
	
	
	static String[] findDistributor(String partId, String quantity, Connection con) {
		String ret[] = new String[2];
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet r1=null;
		ResultSet r2=null;
		int c1=0,c2=0;
		System.out.println("In find distributor");
		try {
			pstmt1 = con.prepareStatement("select D1 from PARTS where P_ID=?");
		
		
		
			pstmt1.setString(1,partId);
		
		
			pstmt2 = con.prepareStatement("select D2 from PARTS where P_ID=?");
		
		
		     pstmt2.setString(1,partId);
		     r1=pstmt1.executeQuery();
		     r2=pstmt2.executeQuery();
		     r1.next();
		     r2.next();
		     String c1_string = r1.getString(1);
	    	 	 System.out.println(c1_string);
	    	 	 String c2_string = r2.getString(1);
	    	 	 System.out.println(c2_string);
	    	 	 
		     if(c1_string != null) {      	 	
	    	 		ret[0]="D0001";
				ret[1]= c1_string;
				System.out.println("returning");
				return ret;	
		     } else if (c2_string != null){
		    	 		ret[0]="D0002";
					ret[1]=c2_string;
					return ret;	
		     } 

		   
		
		
//		if(c1 == -99999) {
//			ret[0]="D0002";
//			ret[1]=Integer.toString(c2);
//			return ret;
//		}
//		else if(c2 == -99999 ) {
//			System.out.println("Should come here ret");
//			ret[0]="D0001";
//			ret[1]=Integer.toString(c1);
//			return ret;
//		} else {
//			try {
//				
//				if(c1 > c2) {
//					System.out.println("Inside comparison");
//					ret[0]="D0002";
//					ret[1]=Integer.toString(c2);
//					return ret;
//				}
//				else {
//					ret[0]="D0001";
//					ret[1]=Integer.toString(c1);
//					return ret;
//					
//				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		//TODO 
		// check the distributor for part id. return distributor id.
		
		
		return ret;
	}
	
	
	
}
