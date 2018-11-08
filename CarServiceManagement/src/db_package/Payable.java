package db_package;

interface Payable {

	String lastPaymenDate();
	void update_no_days_worked(int days);
	int get_no_of_days_worked();
	
}

