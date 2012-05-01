package Config;

public class Texts {
	public static final String WELCOME_MESSAGE = 	"Welcome to Kitchen Inventory Tracker\n" +
													"Please note that you can enter 'h' or 'help' at the prompt to get a full list of commands\n";
	public static final String SHUTDOWN_MESSAGE =	"Shutting down...\n";
	public static final String HELP_MENU = 			"\r\nEnter one of the following commands (or shortcut):\r\n" + 
													"scan (s) - starts scanning\r\n" + 
													"quit (q) - quits the program\r\n" + 
													"help (h) - shows this help\r\n" +
													"export (e) - starts the export list process\n";
	public static final String START_SCANMODE = 	"Starting scan mode...\n";
	public static final String EXIT_SCANMODE = 		"Exiting scan mode ...\n";
	public static final String INVALID_UPC = 		"Invalid UPC";
	public static final String PROMPT_CMD = 		"Enter command> ";
	public static final String PROMPT_SCAN_QUIET = 	">";
	public static final String PROMPT_SCAN_VERBOSE ="Scan Mode > ";
	public static final String HELP_MENU_SCAN = 	"Usage: \n" +
													" Scan items with barcode scanner to enter items\n" +
													" 's' to exit scan mode\n" +
													" 'q' to activate quiet mode\n" + 
													" 'u' to undo last action\n" +
													" 'h' to show available actions\n";
	
	public static final String EXPORT_PROMPT =		"How do you want to export the inventory (f for file, u for screen output)?";
	public static final String INVALID_EXPORT_TYPE ="Invalid export type.\n";
	public static final String PROMPT_NAME_VERBOSE ="Unknown UPC code.\nPlease enter the name of the item: ";
	public static final String PROMPT_NAME_QUIET =	"name: ";
	public static final String PROMPT_AMOUNT_VERBOSE="What's the amount of the item?";
	public static final String PROMPT_AMOUNT_QUIET ="amount: ";
	public static final String IDLE_SEARCHING_QUIET = "searching...\n";
	public static final String IDLE_SEARCHING_VERBOSE = "Not found in local DB, hitting cloud...\n";
	public static final String QUIET_MODE_NOTIFY = "Entering quiet mode\n";
	public static final String PROMPT_START_DATE = 	"Enter start date (MM-DD-YYYY)> ";
	public static final String PROMPT_END_DATE = 	"Enter end date or 'now' (MM-DD-YYYY))> ";
	public static final String INVALID_DATE_ERROR = "Invalid date format\n";
	
	/* string builders */
	
	public static String COMMAND_NOT_FOUND(String c){return"Command '" + c.trim() + "' not found. Enter 'h' for help.\n";}; 
	
	/* internal texts */
	public static final String DEV_MODE_NOTIFICATION= "#### YOU ARE IN DEV MODE ####\n";
}
