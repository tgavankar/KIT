package UI;

import Controllers.Controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Config.Config.ModeType;
import Config.Texts;
import adapters.db.sqlite.upcMap.UPCEntry;
import adapters.scanner.KeyboardInScannerAdapter;
import adapters.scanner.ScannerAdapter;

import commands.ExportParameters;
import commands.ExportParameters.ExportType;

public class ConsoleUI implements UI {
	private boolean isRunning = false;
	private Controller controller = null;
	private boolean quiet = false;
	private Scanner scanner;
	
	public ConsoleUI() {
		isRunning = false;
        quiet = false;
        scanner = new Scanner(System.in);
	}
    
    @Override
    public void setController(Controller c){
        this.controller = c;
    }
	
	@Override
	public void showMainScreen() {
		if(Config.Config.Mode == ModeType.Dev)
			System.out.print(Texts.DEV_MODE_NOTIFICATION);
		System.out.print(Texts.WELCOME_MESSAGE);
		isRunning = true;
	}
    
	@Override
	public boolean isRunning() {
		return isRunning;
	}
    
	@Override
	public String getCommand(String p) {
		System.out.print(p);
		return scanner.next().trim().toLowerCase();
	}
    
	@Override
	public void showHelp() {
		System.out.print(Texts.HELP_MENU);
		
	}
    
	@Override
	public void stopRunning() {
		System.out.print(Texts.SHUTDOWN_MESSAGE);
		isRunning = false;
	}
    
	@Override
	public void commandNotFound(String c) {
		System.out.print(Texts.COMMAND_NOT_FOUND(c));
	}
    
	@Override
	public void startScanMode() {
        quiet = false;
		System.out.println(Texts.START_SCANMODE);
		scanModeUsage();
        
		ScannerAdapter scanner = new KeyboardInScannerAdapter(this, controller);
		try {
			scanner.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Texts.EXIT_SCANMODE);
	}
    
	@Override
	public void scannedItem(UPCEntry upc) {
		if(upc == null) {
			System.out.println(Texts.INVALID_UPC);
		}
		else {
            if(quiet){
                System.out.println("Added " + upc.getItemName());
            } else {
                System.out.println("Added: " + upc.toString());
            }
		}
	}
    
	public String getScanModePrompt() {
        if(quiet){
            return Texts.PROMPT_SCAN_QUIET;
        } else {
        	return Texts.PROMPT_SCAN_VERBOSE;
        }
	}

	public String getMainMenuPrompt(){
		return Texts.PROMPT_CMD;
	}
    public void scanModeUsage(){
        System.out.print(Texts.HELP_MENU_SCAN);
    }
    
    public void toggleQuietMode(){
        quiet = !quiet;
    }
    
	@Override
	public ExportParameters getExportParameters() {
		ExportParameters out = new ExportParameters();
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

		ExportType exportType = ExportType.Other;
		
		while(exportType == ExportType.Other){
			System.out.print(Texts.EXPORT_PROMPT);
			String type = scanner.next().trim().toLowerCase();
			if(type.equals("email") || type.equals("e")) exportType = ExportType.Email;
			else if (type.equals("file") || type.equals("f")) exportType = ExportType.File;
			else if (type.equals("ui") || type.equals("u")) exportType = ExportType.UI;
			else{
				System.out.print(Texts.INVALID_EXPORT_TYPE);
				exportType = ExportType.Other;
			}
		}
				
		out.type = exportType;
		
		while(out.startDate == 0) {
			System.out.print(Texts.PROMPT_START_DATE);
			String start = scanner.next();
			try {
				out.startDate = df.parse(start).getTime() / 1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(Texts.INVALID_DATE_ERROR);
			} 
		}
		
		while(out.endDate == 0) {
			System.out.print(Texts.PROMPT_END_DATE);
			String end = scanner.next();
			if(end.equals("now")) {
				out.endDate = (new java.util.Date()).getTime() / 1000;
			}
			else {
				try {
					out.endDate = df.parse(end).getTime() / 1000;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println(Texts.INVALID_DATE_ERROR);
				} 
			}
		}
		
		return out;
	}

	@Override
	public UPCEntry promptUnknonwnUPCEntry(String upc) {
        if(quiet){
            System.out.print(Texts.PROMPT_NAME_QUIET);
        } else {
            System.out.print(Texts.PROMPT_NAME_VERBOSE);
        }
		String itemName;
		do{
			itemName = scanner.nextLine().trim();
		}while(itemName.equals(""));
		
        if(quiet){
            System.out.print(Texts.PROMPT_AMOUNT_QUIET);
        } else {
            System.out.print(Texts.PROMPT_AMOUNT_VERBOSE);
        }
        String itemAmount;
        do{
        	itemAmount = scanner.nextLine().trim();
        }while(itemAmount.equals(""));
				
		return new UPCEntry(upc, itemName, itemAmount);
	}
    
    public void promptNetworkQuery(){
        if(quiet){
            System.out.print(Texts.IDLE_SEARCHING_QUIET);
        } else {
            System.out.print(Texts.IDLE_SEARCHING_VERBOSE);
        }
    }
    
    public void promptQuietMode(){
        System.out.print(Texts.QUIET_MODE_NOTIFY);
    }

	@Override
	public void promptClearingInventory() {
		System.out.print(Texts.CLEARING_INV_ENTRIES);		
	}

}
