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
	
	
	
}
