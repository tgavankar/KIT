package adapters.db.sqlite.inventory;

public class InventoryEntry {
	private String upc = null;
	private String itemName = null;
	
	public InventoryEntry(String upc, String itemName){
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
