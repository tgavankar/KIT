package commands;

import Controllers.Controller;

public class CommandNotFoundCommand implements Command {
	Controller Controller;
	String cmd;
	
	public CommandNotFoundCommand(Controller Controller, String c) {
		this.Controller = Controller;
		this.cmd = c;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Controller.commandNotFound(cmd);
	}

}
