package tests.database.upcMap;

import static org.junit.Assert.*;

import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import adapters.db.sqlite.upcMap.UPCDAO;
import adapters.db.sqlite.upcMap.UPCEntry;

public class UPCDBTest {
	private String DBPath = "db/testkit.db";
	private adapters.db.sqlite.upcMap.UPCDAO db;
	
	@Before
	public void initialize(){
		try{
			db = new UPCDAO(DBPath);
		} catch(Exception e){
			e.printStackTrace();
			fail();
		}	
	}
	
	@Test
	public void AddTest() {
		try{
			UPCEntry entry = new UPCEntry("000120000","Whatever");
			assertTrue(db.addEntry(entry));
			assertFalse(db.addEntry(entry));
			assertFalse(db.addEntry(null));
		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void RemoveTest(){
		try{
			UPCEntry entry = new UPCEntry("000120000","Whatever");
			assertFalse(db.removeEntry(new UPCEntry("0102012","ThisDoesNotExist")));
			assertTrue(db.removeEntry(entry));
		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}

}
