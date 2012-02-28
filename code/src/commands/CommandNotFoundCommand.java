package commands;

import UI.UI;

public class CommandNotFoundCommand implements Command {
	UI ui;
	String cmd;
	
	public CommandNotFoundCommand(UI ui, String c) {
		this.ui = ui;
		this.cmd = c;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ui.commandNotFound(cmd);
	}

}
