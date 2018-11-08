package db_package;
import java.sql.*;
public class TestConnection {
	public static void main(String args[]){  
		try{    
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			    
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01","rdange","200259721");
//			Connection con=DriverManager.getConnection(  
//					"jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01","sgshetty","200203904");  
	  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from Test"); 

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(rs.next())  {
			    for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			        Object object = rs.getObject(columnIndex);
			        System.out.printf("%s, ", object == null ? "NULL" : object.toString());
			    }
			    System.out.printf("%n");
			}
            con.commit();
			con.close();  
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		} 
}