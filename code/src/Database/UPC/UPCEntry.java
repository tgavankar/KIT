package Database.UPC;

public class UPCEntry {
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
