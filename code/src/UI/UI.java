package UI;

import adapters.db.sqlite.upcMap.UPCEntry;

import commands.Command;

public interface UI {
	public void showMainScreen();
	
	public boolean isRunning();
	
	public Command getCommand();
	
	public void showHelp();
	
	public void stopRunning();
	
	public void commandNotFound(String cmd);
	
	public void startScanMode();
	
	public void scannedItem(UPCEntry upc);
	
	public void scanModePrompt();
	
	
	// called by outside members 
	public UPCEntry promptUnknonwnUPCEntry(String upc);
	
	// Returns a long[] of size 2, where long[0] = start-time and long[1] = end-time
	public long[] getExportTime();
	
}
