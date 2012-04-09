package UI;

import Controllers.Controller;
import adapters.db.sqlite.upcMap.UPCEntry;

import commands.ExportParameters;

public interface UI {
    
    public void setController(Controller c);
    
	public void showMainScreen();
	
	public boolean isRunning();
	
	public String getCommand();
	
	public void showHelp();
	
	public void stopRunning();
	
	public void commandNotFound(String cmd);
	
	public void startScanMode();
	
	public void scannedItem(UPCEntry upc);
	
	public void scanModePrompt();
	
    public void scanModeUsage();
	
    public void toggleQuietMode();
    
    public void promptNetworkQuery();
    
    public void promptQuietMode();
    
	// called by outside members 
	public UPCEntry promptUnknonwnUPCEntry(String upc);
	
	// Returns a long[] of size 2, where long[0] = start-time and long[1] = end-time
	public ExportParameters getExportParameters();
	
}
