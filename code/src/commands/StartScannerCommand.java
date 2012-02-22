package commands;

import interfaces.Command;
import interfaces.UserInterface;

public class StartScannerCommand implements Command {
	UserInterface ui;
	
	public StartScannerCommand(UserInterface ui) {
		this.ui = ui;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ui.startScanMode();
		
	}

}
