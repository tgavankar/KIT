package commands;

import UI.UI;

public class ShowHelpCommand implements Command {
	UI ui;
	
	public ShowHelpCommand(UI ui) {
		this.ui = ui;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ui.showHelp();
	}

}
