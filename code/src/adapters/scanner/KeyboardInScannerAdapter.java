package adapters.scanner;

import Controllers.Controller;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import UI.UI;
import adapters.db.sqlite.upcMap.UPCEntry;
import commands.Command;
import commands.UndoInventoryEntryCommand;
import commands.UndoUPCEntryCommand;
import java.util.Stack;

public class KeyboardInScannerAdapter implements ScannerAdapter {
	private Scanner scanner;
	private UI ui;
	private Controller cont;
    private Stack undo;
	public KeyboardInScannerAdapter(UI ui, InputStream in, Controller c) {
		this.scanner = new Scanner(in);
		this.ui = ui;
        this.cont = c;
        this.undo = new Stack();
	}

	@Override
	public void run() throws ClassNotFoundException, SQLException, Exception {
		ui.scanModePrompt();
		while(scanner.hasNext()) {
			String padded = scanner.next().trim();
			if(padded.equals("s") || padded.equals("stop")) {
				break;
            } else if (padded.equals("u") || padded.equals("undo")) {
                if(!undo.isEmpty()){
                    Command c = (Command) undo.pop();
                    c.execute();
                }
                ui.scanModePrompt();
                continue;
            } else if (padded.equals("h") || padded.equals("help")) {
                ui.scanModeUsage();
                ui.scanModePrompt();
                continue;
            } else if (padded.equals("q") || padded.equals("quiet")) {
                ui.promptQuietMode();
                ui.toggleQuietMode();
                ui.scanModePrompt();
                continue;
            }
			
			String next = padded.replaceFirst("^0+(?!$)", "");

			if(!Pattern.matches("^[0-9]*$", next)) {
				ui.scannedItem(null);
            } else {
				UPCEntry upc = cont.lookupUPC(next, padded);
                if(upc == null) {
                    cont.addEntry(next);
                    Command c = new UndoUPCEntryCommand(next, cont);
                    undo.push(c);
                }
                // make sure that we have the upc info that is in the database
                upc = cont.lookupUPC(next, padded);
                cont.addToInventory(upc);
				Command c = new UndoInventoryEntryCommand(upc, cont);
                undo.push(c);
                
                ui.scannedItem(upc);
			}
			ui.scanModePrompt();
		}
	}
}
