/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import Controllers.Controller;

/**
 *
 * @author SnorSnor
 */
public class UndoUPCEntryCommand implements Command {
    private String upc;
    private Controller cont;
    
    public UndoUPCEntryCommand(String upc, Controller c){
        this.upc = upc;
        this.cont = c;
    }
    
    @Override
    public void execute(){
        cont.removeUPCEntry(upc);
    }
}
