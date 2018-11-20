package db_package;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class ServiceCenter {
	static int manager;
	String name;
	String sc_tel;
	String sc_address;
    String receptionistid = null;
	String sc_id;
    
    ServiceCenter(String sc_id){
    	
    		this.sc_id = sc_id;
    		
    		//TODO
    		// get from Service center table 
    		// update instance variables
    }
	
	ServiceCenter(String sc_address, String sc_tel){
		this.sc_address = sc_address;
		this.sc_tel = sc_tel;
		// create in dB TODO
		//TODO
		
		DbApplication.ServiceCIDList.add(this.sc_id);
	}
	
	
	public boolean hasReceptionist(Connection conn) {
		
		// check if Receptionist for this service center exists in Employee table.
		//TODO
		
		return false;
	}
	
	public String getReceptionistid(Connection conn) {
		//  if Receptionist.id is null; get from service center table.
		// set this.receptionistid;
		//TODO
		return this.receptionistid;
		
		
	}
	
	boolean partAvailableForDelivery(String Partid, String Quantity) {
		
		
		//TODO check is part available in inventory in given quantity.
		
		
		
		return false;
	}
	
	List<Part> partsInSC() {
		//TODO
		List ret = new ArrayList();
		return ret;
	}

}

