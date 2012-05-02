package UI;

import java.util.List;

import Controllers.Controller;
import adapters.db.sqlite.upcMap.UPCEntry;

import commands.ExportParameters;

public interface UI {
    
    public void setController(Controller c);
    
	public void showMainScreen();
	
	public boolean isRunning();
	
	public String getCommand(String prompt);
	
	public void showHelp();
	
	public void stopRunning();
	
	public void commandNotFound(String cmd);
	
	public void startScanMode();
	
	public void scannedItem(UPCEntry upc);
	
	public String getScanModePrompt();
	
	public String getMainMenuPrompt();
	
    public void scanModeUsage();
	
    public void toggleQuietMode();
    
    public void promptNetworkQuery();
    
    public void promptQuietMode();
    
    public void promptClearingInventory();
    
	// called by outside members 
	public UPCEntry promptUnknonwnUPCEntry(String upc);
	
	// Returns a long[] of size 2, where long[0] = start-time and long[1] = end-time
	public ExportParameters getExportParameters();

	public void startModifyMode();

	public String getEditModePrompt();
	
	public void editModeUsage();

	public void promptEntryExists(String itemName, String amount);
	
	public void listEntries(List<String> list);
}
