package Config;

public class Statics {
	public static final String JDBCPath = "org.sqlite.JDBC";
	public static final String InventoryMapName = "upc_map";
	public static final String InventoryEntryName = "scanned_entries";
	
	public static final String UPCMapName = "upc_map";
	public static final String UPCCustomName = "upc_custom";
	
	public static final String CleanDatabasePath = "db/kit_clean.db";
	public static final String ProdDatabasePath = "db/kit.db";
	public static final String DevDatabasePath = "db/kit_dev.db";
	
	public static final String TestCleanDatabasePath = "db/kit_test_clean.db";
	public static final String TestDatabasePath = "db/kit_test.db"; // CREATED AND DELETED
	
	public static final String ProdJDBCDatabasePath = "jdbc:sqlite:" + ProdDatabasePath;
	public static final String DevJDBCDatabasePath = "jdbc:sqlite:" + DevDatabasePath;
	public static final String TestJDBCDatabasePath = "jdbc:sqlite:" + TestDatabasePath;
	
	public static final String ExportPath = "export/inventorylist.csv";
}
