package modal;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class DatabaseTest {
	private static HighScore hs;
	private static Connection conn;

	@BeforeClass
	public static void start() {
		System.out.println("testi");
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
		
		try {
			java.sql.PreparedStatement query = conn.prepareStatement("INSERT INTO scoreTest (nimi, pisteet) VALUES('Matti', '100')");
			query.executeUpdate();

			java.sql.Statement select = conn.createStatement();
			String sql = "SELECT score, playername, submission_date FROM scoreTest ORDER BY score DESC";
			ResultSet resultset = select.executeQuery(sql);

			String nameResult = (resultset.getString("nimi"));
			
			assertEquals("joni", nameResult);

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@AfterClass
	public void clearTable() {
		try {
			java.sql.PreparedStatement query = conn.prepareStatement("TRUNCATE TABLE scoreTest");
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
}
