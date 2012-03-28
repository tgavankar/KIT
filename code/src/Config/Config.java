package Config;

public class Config {
	public enum UIType {
		GUI,
		Console,
		NVDA
	}
	
	public enum ModeType{
		Prod,
		Dev,
		Test,
		Mock
	}
	public static UIType UIMode = UIType.Console;
	public static ModeType Mode = ModeType.Prod;
	public static boolean withNVDA = false;
}
