import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCExample {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
	//C:\Users\Admin\Downloads\sakila-db\sakila-db
	
	static final String USER = "root";
	static final String PASS = "password";
	
	ArrayList<String> results = new ArrayList<String>();  

	
	public void accessDB() {
		Connection conn = null;
		Statement stmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting to Database ...");
			
			conn = DriverManager.getConnection(DB_URL, USER , PASS);
			
			stmt = conn.createStatement();
			String sql1 = "Select first_name, last_name FROM Actor ";
			ResultSet rs = stmt.executeQuery(sql1);
			
			while(rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String actorName = firstName + lastName;
				results.add(actorName);
			}
			rs.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try{
				if(stmt != null) {stmt.close();}			
			}
			catch(SQLException se) {}
			try {
				if(conn != null) {conn.close();}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.print(results);
		
		System.out.println("Goodbye");
	}
	

	
	
}


