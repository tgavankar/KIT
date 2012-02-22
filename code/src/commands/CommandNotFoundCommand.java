package commands;

import interfaces.Command;
import interfaces.UserInterface;

public class CommandNotFoundCommand implements Command {
	UserInterface ui;
	String cmd;
	
	public CommandNotFoundCommand(UserInterface ui, String c) {
		this.ui = ui;
		this.cmd = c;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ui.commandNotFound(cmd);
	}

}
