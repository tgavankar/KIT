package adapters.db.sqlite.upcMap;

import interfaces.DAOEntryAdapter;

public class UPCEntry implements DAOEntryAdapter {
	private String upc = null;
	private String itemName = null;
	
	public UPCEntry(String upc, String itemName){
		this.upc = upc;
		this.itemName = itemName;
	}
	
	public String getUPC(){
		return upc;
	}
	
	public String getItemName(){
		return itemName;
	}
}
