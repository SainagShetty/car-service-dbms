package src.db_package;


import java.util.*;

class Report {
	
	int r_id;
	int c_id;
	String vehicle_license;
	int serviceCenter_id;
	
	
	List<String> faults = new ArrayList<String>();
	
	Report(int c_id, String vehicle_license, int serviceCenter_id){
		this.c_id = c_id;
	    this.vehicle_license = vehicle_license;
	    this.serviceCenter_id = serviceCenter_id;
	}
	
	void addFaults(String fault){
		this.faults.add(fault);	
	}
	
	
	void generateReport() {
		
	}
}
