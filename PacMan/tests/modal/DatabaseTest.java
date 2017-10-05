package modal;

import java.sql.Connection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {
	private static Connection conn;
	private static HighScore hs;

	@BeforeClass
	public static void start() {
		hs = new HighScore();
		
	}

	@Test
	public void getfromDataBase() {
		hs.selectFromDatabase("scoreTest");
	}

	@Test
	public void postToDataBase() {

		hs.post(10, "matto", "10.06.1993", "scoreTest");

	}

	@AfterClass
	public static void clearTable() {
		try {
			java.sql.PreparedStatement query = conn.prepareStatement("TRUNCATE TABLE scoreTest");
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

}
