package commands;

import Controllers.Controller;

public class QuitCommand implements Command {
	Controller Controller;
	
	public QuitCommand(Controller Controller) {
		this.Controller = Controller;
	}
	
	@Override
	public void execute() {
		Controller.stopRunning();
	}

}
