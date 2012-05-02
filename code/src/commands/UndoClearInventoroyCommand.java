package commands;

import java.util.List;

import adapters.db.sqlite.inventory.InventoryDAO;
import adapters.db.sqlite.inventory.InventoryEntry;

public class UndoClearInventoroyCommand implements Command {
	private List<InventoryEntry> entries = null;
	public UndoClearInventoroyCommand(List<InventoryEntry> entries){
		this.entries = entries;
	}
	@Override
	public void execute() {
		try {
			InventoryDAO dao = InventoryDAO.getInstance();

			for(InventoryEntry entry : entries){
				dao.addEntry(entry);
			}
		} catch (Exception e){
			return;
		}		
	}

}
