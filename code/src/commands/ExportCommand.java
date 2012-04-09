package commands;

import Config.Statics;
import java.util.ArrayList;

import Controllers.Controller;
import exporter.FileExporter;
import exporter.ListGenerator;

public class ExportCommand implements Command {
	Controller Controller;
	
	public ExportCommand(Controller Controller) {
		this.Controller = Controller;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ExportParameters param = Controller.getExportParameters();
		
		switch(param.type){
			case Email: 
				System.err.println("not implemented");
				break;
			case File: 
				FileExporter exp = new FileExporter(param.startDate, param.endDate);
				try {
					exp.export(Statics.ExportPath);
				} catch (Exception e1) {
					System.err.println("encountered IO error while exporting to a file.");
					e1.printStackTrace();
				}
				break;
			case UI: 
				// TODO: need to refactor this to remove println
				ListGenerator listGen = new ListGenerator(param.startDate, param.endDate);
				
				ArrayList<String> list;
				
				try{
					list= listGen.getList();
					
					//TODO: Replace with Email/FileExporter depending on user input
					for(String e : list) {
						System.out.println(e);
					}
				} catch (Exception e1){
					System.err.println("encountered IO error while exporting to a file.");
					e1.printStackTrace();
				}
			default: break; 
		}		
	}	
}
