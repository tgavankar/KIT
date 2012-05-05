package commands;

import Controllers.Controller;

public class EditUPCCommand implements Command{
	private Controller c;
	
	public EditUPCCommand(Controller c){
		this.c = c;
	}
	@Override
	public void execute() {
		c.startModifyMode();
	}
	
}
