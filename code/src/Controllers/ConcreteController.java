/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import UI.UI;
import adapters.db.sqlite.inventory.InventoryDAO;
import adapters.db.sqlite.inventory.InventoryEntry;
import adapters.db.sqlite.upcMap.UPCDAO;
import adapters.db.sqlite.upcMap.UPCEntry;
import au.com.bytecode.opencsv.CSVParser;
import commands.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SnorSnor
 */
public class ConcreteController implements Controller {
    private final static String queryURL = "http://www.searchupc.com/handlers/upcsearch.ashx?request_type=1&access_token=1193424F-517B-4D65-95BA-DD4CDE915945&upc=";
    private UI myUI;
    
    public ConcreteController(UI ui){
        this.myUI = ui;
        if(ui == null){
            throw new IllegalArgumentException();
        }
        ui.setController(this);
    }

    @Override
    public void commandNotFound(String c) {
        myUI.commandNotFound(c);
    }

    @Override
    public void startScanMode() {
        myUI.startScanMode();
    }

    @Override
    public void showHelp() {
        myUI.showHelp();
    }

    @Override
    public void stopRunning() {
        myUI.stopRunning();
    }
    
    @Override
    public void showScreen(){
        myUI.showMainScreen();
    }

    @Override
    public Command getCommand() {
        String command = myUI.getCommand(myUI.getMainMenuPrompt());
        Command cmd;
		if(command.equals("scan") || command.equals("s")) {
			cmd = new StartScannerCommand(this);
		}
		else if(command.equals("help") || command.equals("h")) {
			cmd = new ShowHelpCommand(this);
		}
		else if(command.equals("export") || command.equals("e")) {
			cmd = new ExportCommand(this);
		}
		else if(command.equals("quit") || command.equals("q")) {
			cmd = new QuitCommand(this);
		}
		else if(command.equals("edit") || command.equals("d")){
			cmd = new EditUPCCommand(this);
		}
		else {
			cmd = new CommandNotFoundCommand(this, command);
		}
		return cmd;
    }
    
    @Override
    public boolean isRunning() {
        return myUI.isRunning();
    }
    
    @Override
    public ExportParameters getExportParameters(){
        return myUI.getExportParameters();
    }
    
    @Override
    public UPCEntry lookupUPC(String upc, String query){
        UPCEntry ret = null;
        try{
            UPCDAO upcDAO = UPCDAO.getInstance();
            ret = upcDAO.lookUp(upc);
            if(ret == null) {
                //secondary lookup, i.e. query cloud
                myUI.promptNetworkQuery();
                try {
                    URL cloudDB = new URL(queryURL + query);
                    BufferedReader in = new BufferedReader(new InputStreamReader(cloudDB.openStream()));

                    ArrayList<String> response = new ArrayList<String>();

                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.add(inputLine);
                    }

                    in.close();

                    if(response.size() > 1) {
                        String entry = response.get(1);
                        CSVParser parser = new CSVParser();
                        String[] parsedEntry = parser.parseLine(entry);
                        ret = new UPCEntry(upc, parsedEntry[0], "EACH");
                        upcDAO.addEntry(ret, "online");
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    ret = null;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            ret = null;
        }
        return ret;
    }
    
    @Override
    public boolean addEntry(String upc){
        try {
            UPCDAO upcDAO = UPCDAO.getInstance();
            UPCEntry entry = myUI.promptUnknonwnUPCEntry(upc);
            upcDAO.addEntry(entry, "user");
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean addToInventory(UPCEntry upc){
        try {
            InventoryDAO invDAO = InventoryDAO.getInstance();
            InventoryEntry ientry = new InventoryEntry(upc, (new java.util.Date()).getTime());
            invDAO.addEntry(ientry);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeUPCEntry(String upc) {
        try {
            UPCDAO upcDAO = UPCDAO.getInstance();
            upcDAO.removeEntry(upc);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean removeInventoryEntry(UPCEntry entry) {
        try {
            InventoryDAO invDAO = InventoryDAO.getInstance();
            invDAO.removeEntry(entry);
        } catch (Exception e){
            return false;
        }
        
        return true;
    }

	@Override
	public void clearInventory() {
		myUI.promptClearingInventory();
		InventoryDAO dao = null;
		try {
			dao = InventoryDAO.getInstance();						
			for(InventoryEntry entry : dao.getAll()){
				dao.removeEntry(entry.getUPC());
			}	
		} catch (Exception e) {
			System.err.println("encountered SQL error while getting a DAO.");
			e.printStackTrace();
			return;
		}	
	}

	@Override
	public void startModifyMode() {
		myUI.startModifyMode();		
	}

	@Override
	public void listEntries(List<String> list) {
		myUI.listEntries(list);		
	}
}
