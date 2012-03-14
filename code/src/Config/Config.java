package Config;

public class Config {
	public enum UIType {
		GUI,
		Console
	}
	
	public static UIType UIMode = UIType.Console;
	public static boolean DeveloperMode = false;
}
