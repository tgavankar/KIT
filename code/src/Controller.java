import UI.ConsoleUI;
import UI.GraphicUI;
import UI.UI;
import Config.*;
import Config.Config.UIType;
import commands.Command;


public class Controller  {

	public static void main (String args[]) {
		UI ui;
		
		/* parse commandline arguments */
		for(int i = 0 ; i < args.length ; i++){
			if(args[i].equals("console")) {
				Config.UIMode = UIType.Console;
			}else if(args[i].equals("gui")) {
				Config.UIMode = UIType.GUI;
			}else if(args[i].equals("dev")){
				Config.DeveloperMode = true;
			}else if(args[i].equals("prod")){
				Config.DeveloperMode = false;
			}else {
				System.out.println("Invalid command line argument");
				return;
			}
		}		
		
		/* initialize with config elements */
		
		switch(Config.UIMode){
			case GUI: ui = new GraphicUI(); break;
			default: ui = new ConsoleUI(); break; // default to console
		}
		
		ui.showMainScreen();
		
		while(ui.isRunning()) {
			Command cmd = ui.getCommand();
			cmd.execute();
		}
	}
}
