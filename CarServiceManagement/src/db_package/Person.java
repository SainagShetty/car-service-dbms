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
	public boolean login(String Emailid, String Password) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoggedIn = false;
		try{
			pstmt = conn.prepareStatement("SELECT emailid, userid, role FROM person WHERE emailid=? AND password=?");
			pstmt.setString(1, Emailid);
			pstmt.setString(2, Password);
			rs = pstmt.executeQuery();
			while(rs.next())  {
				this.emailID = rs.getString(1);
				this.userID = rs.getString(2);
				String role = rs.getString(3);
				if(role.equals("Manager")) {
					this.my_role = 1;
				}
				else if(role.equals("Receptionist")) {
					this.my_role = 2;
				}
				else if(role.equals("Mechanic")) {
					this.my_role = 3;
				}
				else if(role.equals("Customer")) {
					this.my_role = 4;
				}
				LoggedIn = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return LoggedIn;
		
	}
	
	
}
