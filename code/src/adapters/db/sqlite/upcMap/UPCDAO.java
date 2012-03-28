package adapters.db.sqlite.upcMap;

import java.sql.SQLException;

abstract public class UPCDAO {
	private static UPCDAO singleton = null;
	
	public static UPCDAO getInstance() throws ClassNotFoundException, SQLException{
		if(singleton == null){
			switch(Config.Config.Mode){
				case Prod: singleton = new DBUPCDAO(Config.Statics.ProdDatabasePath); break;
				case Dev: singleton = new DBUPCDAO(Config.Statics.DevDatabasePath); break;
				case Test: singleton = new DBUPCDAO(Config.Statics.TestDatabasePath); break;
				case Mock: singleton = new MockUPCDAO(); break;
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
	
	abstract public boolean addEntry(UPCEntry entry, String source);
	abstract public UPCEntry lookUp(String upc);
	abstract public boolean removeEntry(UPCEntry entry);
}