package tests.database.upcMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Config.Config.ModeType;
import adapters.db.sqlite.upcMap.UPCDAO;
import adapters.db.sqlite.upcMap.UPCEntry;

public class UPCDBTest {
	private UPCDAO db;
	
	@Before
	public void initialize(){
		try{
			Config.Config.Mode = ModeType.Dev;
			db = UPCDAO.getInstance();
		} catch(Exception e){
			e.printStackTrace();
			fail();
		}	
	}
	
	@Test
	public void AddTest() {
		try{
			UPCEntry entry = new UPCEntry("000120000","Whatever","EACH");
			assertTrue(db.addEntry(entry, "user"));
			assertFalse(db.addEntry(entry, "user"));
			assertFalse(db.addEntry(null, "user"));
		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void RemoveTest(){
		try{
			UPCEntry entry = new UPCEntry("000120000","Whatever","EACH");
			assertFalse(db.removeEntry(new UPCEntry("0102012","ThisDoesNotExist","EACH")));
			assertTrue(db.removeEntry(entry));
		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
