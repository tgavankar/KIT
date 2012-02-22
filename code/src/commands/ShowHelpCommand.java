package commands;

import interfaces.Command;
import interfaces.UserInterface;

public class ShowHelpCommand implements Command {
	UserInterface ui;
	
	public ShowHelpCommand(UserInterface ui) {
		this.ui = ui;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ui.showHelp();
	}

}
