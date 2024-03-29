package fall21Jdbc;

import java.sql.*;

public class ExecuteUpdateDt01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
		Statement st = con.createStatement();
		
		//1.Example: Update the number of employees to 10000 if the number of employees is less than the average number of employees
		String sql1 = "UPDATE companies\r\n"
				+ "SET number_of_employees = 15000\r\n"
				+ "WHERE number_of_employees<(SELECT AVG(number_of_employees) FROM companies)";
		
		int numOfUpdatedRecs = st.executeUpdate(sql1);
		System.out.println(numOfUpdatedRecs + " rows updated");
		
		String sql2 = "SELECT * FROM companies";
		ResultSet result2 = st.executeQuery(sql2);
		
		while(result2.next()) {
			System.out.println(result2.getInt(1) + " - " + result2.getString(2) + " - " + result2.getInt(3));
		}
		
		//2.Example: Update the company names to ***** if the number of employees is the highest. 
		//           After updating see updated table on Eclipse console
		
		/*
	 	Note: ? mark in prepared statements can be used just for columns, it cannot be used for tables.
	 */
		String sql3 = "UPDATE companies\r\n"
				+ "SET company = '*****'\r\n"
				+ "WHERE number_of_employees=(SELECT MAX(number_of_employees) FROM companies)";
		
		int numOfUpdatedRecords = st.executeUpdate(sql3);
		System.out.println(numOfUpdatedRecords + " rows updated");
		
		String sql4 = "SELECT * FROM companies";
		ResultSet result4 = st.executeQuery(sql4);
		
		while(result4.next()) {
			System.out.println(result4.getInt(1) + " - " + result4.getString(2) + " - " + result4.getInt(3));
		}

		con.close();
		st.close();
		result2.close();
		result4.close();
	}

}
