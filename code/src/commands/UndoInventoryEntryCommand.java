/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import Controllers.Controller;
import adapters.db.sqlite.upcMap.UPCEntry;

/**
 *
 * @author SnorSnor
 */
public class UndoInventoryEntryCommand implements Command {
    private UPCEntry upc;
    private Controller cont;
    
    public UndoInventoryEntryCommand(UPCEntry upc, Controller c){
        this.upc = upc;
        this.cont = c;
    }

    @Override
    public void execute() {
        cont.removeInventoryEntry(upc);
    }
    
}
