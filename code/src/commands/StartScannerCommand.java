package commands;

import Controllers.Controller;

public class StartScannerCommand implements Command {
	Controller Controller;
	
	public StartScannerCommand(Controller Controller) {
		this.Controller = Controller;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Controller.startScanMode();
		
	}

}
