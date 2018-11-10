package db_package;
import java.sql.*;
class Person implements Loginable {
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
	
	// when logged in 
	Person(Person p){
		this.userID = p.userID;
		this.emailID = p.emailID;
		this.password = p.password;
		this.my_role = p.my_role; 
		this.LoggedIn = true;
	}
	
	Person(String emailID, Connection conn){
		// fetch from db
	}
	
	public void signup() {
		
	}
	
	public String getUserID() {
		return this.userID;
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
	

	
	public void signout() {
		LoggedIn = false;
	}
	
	private void store_db(Connection conn) {
		
		
	}
	
	Boolean personDelete() {
		
		// delete entry from Persons table. Should be called after deleting from Customer or Employee
		return true;
	}

	@Override
	public boolean login(String UserId, String Password) {
		// TODO Auto-generated method stub
		LoggedIn = true;
		return true;
		
	}
	
	
}
