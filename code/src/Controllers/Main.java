package Controllers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import UI.ConsoleUI;
import UI.GraphicUI;
import UI.UI;
import Config.*;
import Config.Config.UIType;
import Config.Config.ModeType;
import commands.Command;


public class Main  {

	public static void main (String args[]) {
		UI ui;
		Process nvdap = null;
		
		/* parse commandline arguments */
		for(int i = 0 ; i < args.length ; i++){
			if(args[i].toLowerCase().equals("console")) {
				Config.UIMode = UIType.Console;
			}else if(args[i].toLowerCase().equals("gui")) {
				Config.UIMode = UIType.GUI;
			}else if(args[i].toLowerCase().equals("dev")){
				Config.Mode = ModeType.Dev;
			}else if(args[i].toLowerCase().equals("prod")){
				Config.Mode = ModeType.Prod;
			}else if(args[i].toLowerCase().equals("nvda")) {
				if(!System.getProperty("os.name").toLowerCase().startsWith("win")) {
					System.out.println("NVDA is only supported on Windows");
					return;
				}
				Config.withNVDA = true;
				Runtime rt = Runtime.getRuntime();
				try {
					nvdap = rt.exec("cmd /c start NVDA/nvda.exe");
					//nvdap = new ProcessBuilder("NVDA/nvda.exe").start();
				} catch (IOException e) {
					// TODO Auto-generated catch block

					Config.withNVDA = false;
				}
				
				if(Config.withNVDA) {
					// TODO: Make handler for shutting down NVDA on dirty shutdown
					//rt.addShutdownHook(new CleanupNVDA());
				}
				
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
		
		Controller c = new ConcreteController(ui);
        
        c.showScreen();
		while(c.isRunning()) {
			Command cmd = c.getCommand();
			cmd.execute();
		}
		
		if(Config.withNVDA && nvdap != null) {
			Robot r;
			try {
				r = new Robot();
				r.keyPress(KeyEvent.VK_INSERT);
				r.keyPress(KeyEvent.VK_Q);
			} catch (AWTException e) {
				// TODO Auto-generated catch block

			}
		}
	}
}
