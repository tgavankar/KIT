package commands;

import UI.UI;

public class StartScannerCommand implements Command {
	UI ui;
	
	public StartScannerCommand(UI ui) {
		this.ui = ui;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ui.startScanMode();
		
	}

}
