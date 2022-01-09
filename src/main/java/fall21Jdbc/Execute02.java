package fall21Jdbc;

import java.sql.*;

public class Execute02 {
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	//On SQL Developer by typing "SHOW Connection" you can find the url
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
    	
    	Statement st = con.createStatement();
    	
    	//1.Example: Select the country names whose region id’s are 1
    	String sq1 = "SELECT country_name FROM countries WHERE region_id=1";
    	//IF you use execute () method, you will get just true or false  but I wantto see records
    	boolean r1 = st.execute(sq1);
    	System.out.println("execute() method returned: " + r1+ " for select query");
    	
    	//To see the records we have another method which is "executeQuery()"
    	ResultSet result1 = st.executeQuery(sq1);
    	
    	while(result1.next()) {
    		System.out.println(result1.getString("country_name"));
    		
    	}
    	//2.Example:Select the country ids and country names whose region id’s are greater than 2
    	String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id>2";
    	ResultSet result2 = st.executeQuery(sql2);
    	
    	while(result2.next()) {
    		System.out.println(result2.getString("country_name") +"-->" + result2.getString("country_id"));
    	}
    	//3.Example; Select the worker whose salary is the lowest from workers table
    	String sql3 = "SELECT * FROM workers WHERE salary = (SELECT MIN(salary)FROM workers)";
    	ResultSet result3 = st.executeQuery(sql3);
    	while (result3.next()) {
    		System.out.println(result3.getString("id") + " -->"+result3.getString("name")+" "+result3.getInt("salary"));
    	}
    	
    	//3.Example: Select the company whose number of employees is the lowest from companies table
        String sql4 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet result4 = st.executeQuery(sql4);
        
        while(result4.next()) {
            System.out.println(result4.getInt(1) + " - " + result4.getString(2) + " - " + result4.getInt(3));
        }
    	
    	con.close();
    	st.close();
    	result1.close();
    	result2.close();
    	result3.close();
    	result4.close();
    	
    	
    	
}
}