package UI;

import Controllers.Controller;
import adapters.db.sqlite.upcMap.UPCEntry;

import commands.ExportParameters;

public class GraphicUI implements UI {

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
	public ExportParameters getExportParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UPCEntry promptUnknonwnUPCEntry(String upc) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void setController(Controller c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCommand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void scanModeUsage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void toggleQuietMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void promptNetworkQuery() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void promptQuietMode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
