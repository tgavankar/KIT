package adapters.db.sqlite.inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import adapters.db.sqlite.upcMap.UPCEntry;

public class InventoryDAO {
	private static InventoryDAO singleton = null;
	
	private Connection conn = null;
	private static final String mapName = Config.Statics.InventoryMapName;
	private static final String inventoryName = Config.Statics.InventoryEntryName;
	
	// path by default should be db/test.db
	//TODO: stop throwing exceptions and gracefully handle them everywhere
	private InventoryDAO() throws ClassNotFoundException, SQLException{		
		Class.forName(Config.Statics.JDBCPath);
		conn = DriverManager.getConnection(Config.Config.DeveloperMode?Config.Statics.DevDatabasePath:Config.Statics.ProdDatabasePath);
	}
	
	public static synchronized InventoryDAO getInstance() throws Exception{
		if(singleton == null) return (singleton = new InventoryDAO());
		else return singleton;
	}
	
	public ArrayList<InventoryEntry> lookUp(long start, long end) {
		PreparedStatement ps = null;
		try {
			String select = "SELECT " + mapName + ".id, " + mapName + ".upc, " + mapName + ".name, " + mapName + ".amount, " + inventoryName + ".created " +
							"FROM " + mapName + ", " + inventoryName + 
							" WHERE " + mapName + ".id=" + inventoryName + ".upc_id AND " + inventoryName + ".created > ? AND " + inventoryName + ".created < ?";
			ps = conn.prepareStatement(select);
			ps.setLong(1, start);
			ps.setLong(2, end);
			ResultSet rs = ps.executeQuery();
			ArrayList<InventoryEntry> out = new ArrayList<InventoryEntry>();
			while(rs.next()) {
				out.add(new InventoryEntry(new UPCEntry(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4)), rs.getString(5)));
			}
			return out;
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
	
	public boolean addEntry(InventoryEntry entry) {
		PreparedStatement ps = null;
		int count = 0;
		java.util.Date now = new java.util.Date();
		long time = now.getTime() / 1000;
		
		try {
			conn.setAutoCommit(false);
			String insert = "INSERT INTO " + inventoryName + " (upc_id, created) VALUES (?, ?)";
			ps = conn.prepareStatement(insert);
			ps.setString(1, Integer.toString(entry.getUPC().getID()));
			ps.setString(2, Long.toString(time));
			count = ps.executeUpdate();
			
			conn.commit();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			
	        if (conn != null) {
	            try {
	                System.out.println("Transaction is being rolled back");
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
	       
	        try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return (count != 0);
	}
}
