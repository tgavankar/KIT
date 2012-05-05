package adapters.scanner;

import Controllers.Controller;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import UI.UI;
import adapters.db.sqlite.inventory.InventoryDAO;
import adapters.db.sqlite.upcMap.UPCEntry;
import commands.Command;
import commands.UndoClearInventoroyCommand;
import commands.UndoInventoryEntryCommand;
import commands.UndoUPCEntryCommand;
import java.util.Stack;

public class UPCKeyboardInScannerAdapter implements ScannerAdapter {
	private UI ui;
	private Controller cont;
    private Stack<Command> undo;
    
	public UPCKeyboardInScannerAdapter(UI ui, Controller c) {
		this.ui = ui;
        this.cont = c;
        this.undo = new Stack<Command>();
	}

	@Override
	public void run() throws ClassNotFoundException, SQLException, Exception {
		while(true) {
			String padded = ui.getCommand(ui.getEditModePrompt());
			
			if(padded.equals("s") || padded.equals("stop")) {
				break;
            }
			
			String next = padded.replaceFirst("^0+(?!$)", "");

			if(!Pattern.matches("^[0-9]*$", next)) {
				ui.scannedItem(null);
            } else {
				UPCEntry upc = cont.lookupUPC(next, padded);
				if(upc != null){
					ui.promptEntryExists(upc.getItemName(), upc.getAmount());
				}
				cont.removeUPCEntry(next);
				cont.addEntry(next);	
			}			
			
		}
	}
}
