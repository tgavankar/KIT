package exporter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.sql.SQLException;

import adapters.db.sqlite.inventory.InventoryDAO;
import adapters.db.sqlite.inventory.InventoryEntry;

public class FileExporter implements Exporter {
	private long start;
	private long end;
	
	public FileExporter(long start, long end) {
		if(start < 0 || end < 0 || start > end)
			throw new IllegalArgumentException();
		this.start = start;
		this.end = end;
	}
	
	public void export(String path) throws IOException, SQLException, ClassNotFoundException {
		if(path == null || path == "")
			throw new IllegalArgumentException();
		OutputStream out = new BufferedOutputStream(new FileOutputStream(path, false));
		InventoryDAO dao = InventoryDAO.getInstance();
		
		for(InventoryEntry entry : dao.lookUp(start, end))			
			out.write((entry.toCSV() + "\n").getBytes());		
		out.close();
	}

}
