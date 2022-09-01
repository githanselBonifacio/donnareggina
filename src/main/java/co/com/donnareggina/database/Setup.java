package co.com.donnareggina.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Setup {
	
	private String user;
	private String password;
	private String stringConexion;
	protected Connection connection;
	
	
	 
	
	public Setup() {
		super();
	
		this.user = "postgres";
		this.password = "1234";
		this.stringConexion = "jdbc:postgresql://localhost:5432/donnareggina";
		
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(stringConexion, this.user , this.password);
			
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}         
        
	}
	
}
