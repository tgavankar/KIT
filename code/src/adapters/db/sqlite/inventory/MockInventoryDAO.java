package adapters.db.sqlite.inventory;

import adapters.db.sqlite.upcMap.UPCEntry;
import java.util.ArrayList;

public class MockInventoryDAO extends InventoryDAO{
	private ArrayList<InventoryEntry> list;  
	
	public MockInventoryDAO(){
		list = new ArrayList<InventoryEntry>();
	}

	@Override
	public ArrayList<InventoryEntry> lookUp(long start, long end) {
		ArrayList<InventoryEntry> ret = new ArrayList<InventoryEntry>();
		int i = 0;
		for( ; i < list.size() ; i++){
			if(Long.parseLong(list.get(i).getCreated()) > start) break;
		}
		for( ; i < list.size() ; i++ ){
			InventoryEntry e = list.get(i);
			if (Long.parseLong(e.getCreated()) > end) break;
			else ret.add(e);
		}
		return ret;
	}

	@Override
	public boolean addEntry(InventoryEntry entry) {
		int i = 0;
		for(InventoryEntry e : list){
			if(Long.parseLong(e.getCreated()) > Long.parseLong(entry.getCreated()))
				break;
			i++;
		}
		
		list.add(entry);
		
		return false;
	}

    @Override
    public boolean removeEntry(UPCEntry entry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
	public ArrayList<InventoryEntry> getAll() {
		return list;
	}
}
