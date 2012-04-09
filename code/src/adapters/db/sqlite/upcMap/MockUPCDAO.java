package adapters.db.sqlite.upcMap;

import java.sql.SQLException;
import java.util.HashMap;

import Config.Statics;

public class MockUPCDAO extends UPCDAO {
	private DBUPCDAO upcDAO = null;
	private HashMap<String, UPCEntry> addedMap = null;
	private HashMap<String, UPCEntry> removedMap = null;
	
	public MockUPCDAO() throws ClassNotFoundException, SQLException{
		upcDAO = new DBUPCDAO(Statics.ProdDatabasePath);
		addedMap = new HashMap<String, UPCEntry>();
		removedMap = new HashMap<String, UPCEntry>();
	}
	
	@Override
	public boolean addEntry(UPCEntry entry, String source) {
		if(removedMap.containsKey(entry.getUPC())){
			removedMap.remove(entry.getUPC());
		}
		addedMap.put(entry.getUPC(), entry);
		return true;
	}

	@Override
	public UPCEntry lookUp(String upc) {
		if(removedMap.containsKey(upc)) return null;
		else if(addedMap.containsKey(upc)) return addedMap.get(upc);		
		else return upcDAO.lookUp(upc);
	}

	@Override
	public boolean removeEntry(String entry) {
		if(addedMap.containsKey(entry)) {
			addedMap.remove(entry);
			return true;
		}else{
			removedMap.put(entry, null);
			return true;
		}
	}
}
