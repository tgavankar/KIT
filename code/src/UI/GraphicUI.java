package UI;

import adapters.db.sqlite.upcMap.UPCEntry;
import interfaces.Command;
import interfaces.UserInterface;

public class GraphicUI implements UserInterface {

	@Override
	public void showMainScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command getCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showHelp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopRunning() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commandNotFound(String c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startScanMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scannedItem(UPCEntry upc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scanModePrompt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long[] getExportTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
