package UI;

import Controllers.Controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Config.Config.ModeType;
import adapters.db.sqlite.upcMap.UPCEntry;
import adapters.scanner.KeyboardInScannerAdapter;
import adapters.scanner.ScannerAdapter;

import commands.ExportParameters;
import commands.ExportParameters.ExportType;

public class ConsoleUI implements UI {
	private boolean isRunning;
    private Controller c;
	private boolean quiet;
    
	public ConsoleUI() {
		isRunning = false;
        quiet = false;
	}
    
    @Override
    public void setController(Controller c){
        this.c = c;
    }
	
	@Override
	public void showMainScreen() {
		if(Config.Config.Mode == ModeType.Dev)
			System.out.println("#### YOU ARE IN DEV MODE ####");
		System.out.println("Welcome to Kitchen Inventory Tracker");
		System.out.println("Please note that you can enter 'h' or 'help' at the prompt to get a full list of commands");
		isRunning = true;
	}
    
	@Override
	public boolean isRunning() {
		return isRunning;
	}
    
	@Override
	public String getCommand() {
		System.out.print("Enter command> ");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.next().trim().toLowerCase();
		return command;
	}
    
	@Override
	public void showHelp() {
		System.out.println("\r\nEnter one of the following commands (or shortcut):\r\n" + 
				"scan (s) - starts scanning\r\n" + 
				"quit (q) - quits the program\r\n" + 
				"help (h) - shows this help\r\n" +
				"export (e) - starts the export list process");
		
	}
    
	@Override
	public void stopRunning() {
		System.out.println("Shutting down...");
		isRunning = false;
	}
    
	@Override
	public void commandNotFound(String c) {
		System.out.println("Command '" + c + "' not found. Enter 'h' for help.");
		
	}
    
	@Override
	public void startScanMode() {
        quiet = false;
		System.out.println("Starting scan mode...");
		scanModeUsage();
        
		ScannerAdapter scanner = new KeyboardInScannerAdapter(this, System.in, c);
		try {
			scanner.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting scan mode...");
	}
    
	@Override
	public void scannedItem(UPCEntry upc) {
		if(upc == null) {
			System.out.println("Invalid UPC");
		}
		else {
            if(quiet){
                System.out.println("Added " + upc.getItemName());
            } else {
                System.out.println("Added: " + upc.toString());
            }
		}
	}
    
	public void scanModePrompt() {
        if(quiet){
            System.out.print(">");
        } else {
            System.out.print("Scan Mode > ");
        }
	}
    
    public void scanModeUsage(){
        System.out.println("Usage: ");
        System.out.println(" Scan items with barcode scanner to enter items");
        System.out.println(" 's' to exit scan mode");
        System.out.println(" 'q' to activate quiet mode");
        System.out.println(" 'u' to undo last action");
        System.out.println(" 'h' to show available actions");
    }
    
    public void toggleQuietMode(){
        quiet = !quiet;
    }
    
	@Override
	public ExportParameters getExportParameters() {
		ExportParameters out = new ExportParameters();
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Scanner scanner = new Scanner(System.in);

		ExportType exportType = ExportType.Other;
		
		while(exportType == ExportType.Other){
			System.out.println("How do you want to export the inventory (e for email, f for file, u for screen output)?");
			String type = scanner.next().trim().toLowerCase();
			if(type.equals("email") || type.equals("e")) exportType = ExportType.Email;
			else if (type.equals("file") || type.equals("f")) exportType = ExportType.File;
			else if (type.equals("ui") || type.equals("u")) exportType = ExportType.UI;
			else{
				System.out.println("Invalid export type.");
				exportType = ExportType.Other;
			}
		}
				
		out.type = exportType;
		
		while(out.startDate == 0) {
			System.out.print("Enter start date (MM-DD-YYYY)>");
			String start = scanner.next();
			try {
				out.startDate = df.parse(start).getTime() / 1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid date format");
			} 
		}
		
		while(out.endDate == 0) {
			System.out.print("Enter end date or 'now' (MM-DD-YYYY))>");
			String end = scanner.next();
			if(end.equals("now")) {
				out.endDate = (new java.util.Date()).getTime() / 1000;
			}
			else {
				try {
					out.endDate = df.parse(end).getTime() / 1000;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Invalid date format");
				} 
			}
		}
		
		return out;
	}

	@Override
	public UPCEntry promptUnknonwnUPCEntry(String upc) {
		Scanner scanner = new Scanner(System.in);
		
        if(quiet){
            System.out.println("name:");
        } else {
            System.out.println("Unknown UPC code.\nPlease enter the name of the item:");
        }
		String itemName = scanner.nextLine().trim();
		
        if(quiet){
            System.out.println("amount:");
        } else {
            System.out.println("What's the amount of the item?");
        }
        String itemAmount = scanner.nextLine().trim();
				
		return new UPCEntry(upc, itemName, itemAmount);
	}
    
    public void promptNetworkQuery(){
        if(quiet){
            System.out.println("searching...");
        } else {
            System.out.println("Not found in local DB, hitting cloud...");
        }
    }
    
    public void promptQuietMode(){
        System.out.println("Entering quiet mode");
    }

}
