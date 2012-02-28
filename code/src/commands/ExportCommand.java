package commands;

import java.util.ArrayList;

import UI.UI;
import exporter.ListGenerator;

public class ExportCommand implements Command {
	UI ui;
	
	public ExportCommand(UI ui) {
		this.ui = ui;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		long[] bounds = ui.getExportTime();
		ListGenerator listGen = new ListGenerator(bounds[0], bounds[1]);
		
		ArrayList<String> list = listGen.getList();
		
		//TODO: Replace with Email/FileExporter depending on user input
		for(String e : list) {
			System.out.println(e);
		}
	}

}
