package src.db_package;

interface Loginable {
	void setUserId(String Userid);
	void setPassword(String Password);
	String getUserID();
	boolean login(String UserId, String Password); //fetch from data base for theperson and validate with parameters
	void signup(); // must call setUserid and setPassword.
	void signout(); // does nothing right now.
}
