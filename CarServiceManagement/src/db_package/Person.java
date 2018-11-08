package db_package;
import java.sql.*;
abstract class Person implements Loginable {
	protected String userID;
	String emailID;
    protected String password;
	int my_role;
	protected Boolean LoggedIn = false;
	
	Connection conn;
	static PreparedStatement stmt;

	Person(Connection conn){
		this.conn = conn;
	}
	
	Person(String emailID, Connection conn){
		// fetch from db
	}
	
	Person(String emailID, int role, Connection conn) {
		this.emailID = emailID ;
		this.my_role = role;
		this.conn  = conn;
		// data base entry created by signup()
	}
	
	public void setUserId(String userid) { // must update in db
		this.userID = userid;
	}
	
	public void setPassword(String password) { // must store in db 
		this.password = password;
	}
	
	public void login(String userId, String password) {
		LoggedIn = true;
	} //fetch from data base for the person and validate with parameters
	
	

	
	public void signout() {
		LoggedIn = false;
	}
	
	private void store_db(Connection conn) {
		
		
	}
	
	Boolean personDelete() {
		
		// delete entry from Persons table. Should be called after deleting from Customer or Employee
		return true;
	}
	
	
}
