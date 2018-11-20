package db_package;

public class Role {
	    Role() { }  // Prevents instantiation
	    public static final int MANAGER   = 1;
	    public static final int RECEPTIONIST = 2;
	    public static final int MECHANIC     = 3;
	    public static final int CUSTOMER     = 4;
	    
	    public static String getRole(int role)
	    {
	    	if(role == 1)
				return "Manager";
			if(role == 2)
				return "Receptionist";
			if(role == 3)
				return "Mechanic";
			if(role == 4)
				return "Customer";
			return "";
	    }
}

