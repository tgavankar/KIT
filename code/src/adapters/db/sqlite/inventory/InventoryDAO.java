package adapters.db.sqlite.inventory;

import java.sql.SQLException;

public abstract class InventoryDAO {

	private static InventoryDAO singleton = null;

	public static InventoryDAO getInstance() throws ClassNotFoundException, SQLException{
		if(singleton == null){
			switch(Config.Config.Mode){
				case Prod: singleton = new DBInventoryDAO(Config.Statics.ProdDatabasePath); break;
				case Dev: singleton = new DBInventoryDAO(Config.Statics.DevDatabasePath); break;
				case Test: singleton = new DBInventoryDAO(Config.Statics.TestDatabasePath); break;
				case Mock: singleton = new MockInventoryDAO(); break;
			}
			return singleton;
		}else return singleton;
	}
	
	/**
	 * this should be only called in the test cases for tear down purposes.
	 */
	public static void removeInstance(){
		singleton = null;	
	}
	
	public abstract Iterable<InventoryEntry> lookUp(long start, long end);
	public abstract boolean addEntry(InventoryEntry entry);
}