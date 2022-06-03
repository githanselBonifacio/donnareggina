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
	
		this.user = "atkzsgcztqzogi";
		this.password = "7284927f5839ecdcc9aae2d0ba65f47d20e88c864cf4a5c8ac12af56f0164e30";
		this.stringConexion = "jdbc:postgresql://ec2-54-227-248-71.compute-1.amazonaws.com:5432/d8oiuvucpcg0qs";
		
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(stringConexion, this.user , this.password);
			
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}         
        
	}
	
}
