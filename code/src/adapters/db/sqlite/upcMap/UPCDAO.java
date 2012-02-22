package adapters.db.sqlite.upcMap;
import interfaces.DAOAdapter;

import java.sql.*;

public class UPCDAO implements DAOAdapter {
	private Connection conn = null;
	public static final String TableName = "";
	
	// path by default should be db/test.db
	public UPCDAO (String path) throws ClassNotFoundException, SQLException{		
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:" + path);
	}
	
	public UPCEntry lookUp(String upc) throws SQLException{
		Statement stat = conn.createStatement();
		// TODO
		return null;
	}
	
	public boolean addEntry(UPCEntry entry) throws SQLException{
		Statement stat = conn.createStatement();
		// TODO
		return false;
	}
	
	public boolean removeEntry(UPCEntry entry) throws SQLException{
		Statement stat = conn.createStatement();
		// TODO
		return false;
	}
}