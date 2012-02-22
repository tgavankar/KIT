package adapters.db.sqlite.upcMap;


public class UPCEntry {
	private int id = 0;
	private String upc = null;
	private String itemName = null;
	private String amount = null;
	
	public UPCEntry(int id, String upc, String itemName, String amount) {
		this.id = id;
		this.upc = upc;
		this.itemName = itemName;
		this.amount = amount;
	}
	
	public UPCEntry(String upc, String itemName, String amount) {
		this.upc = upc;
		this.itemName = itemName;
		this.amount = amount;
	}
	
	public int getID() {
		return id;
	}
	
	public String getUPC() {
		return upc;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getAmount() {
		if(amount == null) {
			return "EACH";
		}
		return amount;
	}
	
	public String toString() {
		return "[UPC: " + getUPC() + "] " + getItemName() + " | " + getAmount();
	}
}
