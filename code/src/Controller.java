import interfaces.Command;
import interfaces.UserInterface;
import UI.ConsoleUI;
import UI.GraphicUI;


public class Controller  {
	public static void main (String args[]) {
		if(args.length != 1) {
			System.out.println("No arg found");
			return;
		}
		
		UserInterface ui;
		if(args[0].equals("console")) {
			ui = new ConsoleUI();
		}
		else if(args[0].equals("gui")) {
			ui = new GraphicUI();
		}
		else {
			System.out.println("Invalid command line argument");
			return;
		}
		
		ui.showMainScreen();
		
		while(ui.isRunning()) {
			Command cmd = ui.getCommand();
			cmd.execute();
		}
	}
}