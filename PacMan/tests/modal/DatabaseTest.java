package modal;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {
	private static HighScore hs;

	@BeforeClass
	public static void start() {
		hs = new HighScore();
	}
	@Before
	public void post() {
		hs.post(10, "matto", hs.currentDate(), "scoreTest");
		hs.selectFromDatabase("scoreTest");
	}
	@Test
	public void getfromDataBase() {
		hs.selectFromDatabase("scoreTest");
	}
	@Test
	public void nameFromDatabase() {
		ArrayList<String> name = hs.getName();
		assertEquals("nimi ei tuu oikein", "matto", name.get(0));	
	}
	@Test
	public void scoreFromDataBase() {
		ArrayList<String> score = hs.getScore();
		assertEquals("pisteet ei tuu oikein", "10", score.get(0));
	}
	@Test
	public void postToDataBase() {
		assertEquals(true, hs.post(10, "matto", hs.currentDate(), "scoreTest"));
	}
	
	@AfterClass
	public static void clearTable() {
		hs.clearTable();
	}
	
	
}
