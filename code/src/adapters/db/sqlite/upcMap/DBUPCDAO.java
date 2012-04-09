package adapters.db.sqlite.upcMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUPCDAO extends UPCDAO  {
	private static DBUPCDAO singleton = null;
	
	private Connection conn = null;
	public static final String mapName = Config.Statics.UPCMapName;
	public static final String customName = Config.Statics.UPCCustomName;
	
	// path by default should be db/test.db
	public DBUPCDAO(String path) throws ClassNotFoundException, SQLException {		
		Class.forName(Config.Statics.JDBCPath);
		conn = DriverManager.getConnection(path);
	}
	
	public synchronized UPCEntry lookUp(String upc) {
		PreparedStatement ps = null;
		try {
			String select = "SELECT * FROM " + mapName + " WHERE upc=?";
			ps = conn.prepareStatement(select);
			ps.setString(1, upc);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return new UPCEntry(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps != null) {
		        try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		return null;
	}
	
	//TODO make source enum?
	public synchronized boolean addEntry(UPCEntry entry, String source) {
		if(entry == null || !(source == "user" || source == "online")) {
			return false;
		}
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int count = 0;
		int count2 = 0;
		try {
			conn.setAutoCommit(false);
			String insert = "INSERT INTO " + mapName + " (upc, name, amount) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(insert);
			ps.setString(1, entry.getUPC());
			ps.setString(2, entry.getItemName());
			ps.setString(3, entry.getAmount());
			count = ps.executeUpdate();
			
			java.util.Date now = new java.util.Date();
			long t = now.getTime() / 1000;
			
			String select = "INSERT INTO " + customName + " (upc_id, source, created) VALUES ((select last_insert_rowid()), ?, ?)";
			ps2 = conn.prepareStatement(select);
			ps2.setString(1, source);
			ps2.setString(2, Long.toString(t));
			count2 = ps2.executeUpdate();
			
			conn.commit();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			
	        if (conn != null) {
	            try {
	                System.out.println(
	                    "Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	                excep.printStackTrace();
	            }
	        }
	    } finally {
	        if (ps != null) {
	            try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        if (ps2 != null) {
	            try {
					ps2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return (count != 0 && count2 != 0);
	}
	
	public synchronized boolean removeEntry(UPCEntry entry){
		// TODO
		return false;
	}
}