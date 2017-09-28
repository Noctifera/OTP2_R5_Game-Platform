package modal;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {
	private static HighScore hs;
	private static Connection conn;

	@BeforeClass
	public static void start() {
		hs = new HighScore();

		try {
			String driver = "com.mysql.jdbc.Driver";
			// database osoite
			String connectionUrl = "jdbc:mysql://localhost:2280/highscores";
			String username = "kariants";
			String password = "pacman2017";
			Class.forName(driver);

			// luodaan yhteys
			conn = DriverManager.getConnection(connectionUrl, username, password);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Test
	public void getfromDataBase() {

	}

	@Test
	public void postToDataBase() {

	}
}
