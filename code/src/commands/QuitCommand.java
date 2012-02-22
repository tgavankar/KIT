package commands;

import interfaces.Command;
import interfaces.UserInterface;

public class QuitCommand implements Command {
	UserInterface ui;
	
	public QuitCommand(UserInterface ui) {
		this.ui = ui;
	}
	
	@Override
	public void execute() {
		ui.stopRunning();
	}

}
