package commands;

public class ExportParameters{	
	public enum ExportType{
		Email,
		File,
		UI,
		Other
	}
	
	public long startDate;
	public long endDate;
	public ExportType type;
}