package tests.exporter;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.*;

import exporter.FileExporter;

import adapters.db.sqlite.inventory.InventoryDAO;
import adapters.db.sqlite.upcMap.UPCDAO;

import Config.Config;
import Config.Config.ModeType;
import Config.Statics;

public class FileExporterTest {
	private static String exportTestPath = "export/inventorylist_test.csv";
	private static File exportFile;
	@Before
	public void initialize() throws IOException{
		exportFile = new File(exportTestPath);
		exportFile.createNewFile();
		Config.Mode = ModeType.Mock;
		InventoryDAO.removeInstance();
		UPCDAO.removeInstance();
	}
	
	@Test
	public void simpleExport() throws IOException, Exception{
		Scanner scanner = new Scanner(exportFile);
		FileExporter exporter = new FileExporter(0,0);
		exporter.export(exportTestPath);
		if(scanner.hasNext()) fail();
	}	
	
	@After
	public void tearDown(){
		Config.Mode = ModeType.Prod;
		InventoryDAO.removeInstance();
		UPCDAO.removeInstance();
	}
}
