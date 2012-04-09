package commands;

import Controllers.Controller;

public class ShowHelpCommand implements Command {
	Controller Controller;
	
	public ShowHelpCommand(Controller Controller) {
		this.Controller = Controller;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Controller.showHelp();
	}

}
