package commands;

import UI.UI;

public class QuitCommand implements Command {
	UI ui;
	
	public QuitCommand(UI ui) {
		this.ui = ui;
	}
	
	@Override
	public void execute() {
		ui.stopRunning();
	}

}
