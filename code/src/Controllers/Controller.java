/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.ArrayList;
import java.util.List;

import adapters.db.sqlite.upcMap.UPCEntry;
import adapters.scanner.ScannerAdapter;
import commands.Command;
import commands.ExportParameters;

/**
 *
 * @author SnorSnor
 */
public interface Controller {
    public Command getCommand();
    public void commandNotFound(String c);
    public void startScanMode();
    public void showHelp();
    public void stopRunning();
    public void showScreen();
    public boolean isRunning();
    public ExportParameters getExportParameters();
    public UPCEntry lookupUPC(String upc, String query);
    public boolean addEntry(String upc);
    public boolean addToInventory(UPCEntry upc);
    public boolean removeUPCEntry(String upc);
    public boolean removeInventoryEntry(UPCEntry upc);
    public void clearInventory();
	public void startModifyMode();
	public void listEntries(List<String> list);
}
