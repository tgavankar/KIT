package Tests.Database.UPC;

import static org.junit.Assert.*;

import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import Database.UPC.UPCDB;
import Database.UPC.UPCEntry;

public class UPCDBTest {
	private String DBPath = "db/testkit.db";
	private UPCDB db;
	
	@Before
	public void initialize(){
		try{
			db = new UPCDB(DBPath);
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
