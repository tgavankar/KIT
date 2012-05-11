package commands;

import Config.Statics;
import java.util.ArrayList;

import Controllers.Controller;
import exporter.FileExporter;
import exporter.ListGenerator;

public class ExportCommand implements Command {
	Controller controller;
	
	public ExportCommand(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ExportParameters param = controller.getExportParameters();
		
		switch(param.type){
			case File: 
				FileExporter exp = new FileExporter(param.startDate, param.endDate);
				try {
					exp.export(Statics.ExportPath);
				} catch (Exception e1) {
					System.err.println("encountered IO error while exporting to a file.");

				}
				break;
			case UI: 
				// TODO: need to refactor this to remove println
				ListGenerator listGen = new ListGenerator(param.startDate, param.endDate);
				
				ArrayList<String> list;
				
				try{
					list= listGen.getList();
					controller.listEntries(list);
				} catch (Exception e1){
					System.err.println("encountered IO error while exporting to a file.");

				}
			default: break; 
		}		
	}	
}
