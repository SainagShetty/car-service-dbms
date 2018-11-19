package db_package;
import java.sql.Connection;

class ServiceCenter {
	static int manager;
	String name;
	String sc_tel;
	String sc_address;
    String receptionistid = null;
	String sc_id;
    
    ServiceCenter(String sc_id){
    	
    		// get from Service center table 
    		// update instance variables
    }
	
	ServiceCenter(String sc_address, String sc_tel){
		this.sc_address = sc_address;
		this.sc_tel = sc_tel;	
	}
	
	
	public boolean hasReceptionist(Connection conn) {
		
		// check if Receptionist for this service center exists in Employee table.
		
		
		return false;
	}
	
	public String getReceptionistid(Connection conn) {
		//  if Receptionist.id is null; get from service center table.
		// set this.receptionistid;
		
		return this.receptionistid;
		
		
	}

}

