package Config;

public class Config {
	public enum UIType {
		GUI,
		Console,
		NVDA
	}
	
	public static UIType UIMode = UIType.Console;
	public static boolean DeveloperMode = false;
	public static boolean withNVDA = false;
}
