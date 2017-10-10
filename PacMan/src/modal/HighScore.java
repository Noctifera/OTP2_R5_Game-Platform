package modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 
 * @author kari-antti
 *
 */
public class HighScore {

	/*
	 * java.util.Date date = new java.util.Date();
	 * 
	 * java.text.SimpleDateFormat time = new
	 * java.text.SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * String currentTime = time.format(date);
	 */

	private ArrayList<String> score = new ArrayList<>();
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<String> date = new ArrayList<>();
	
	/**
	 * Getter for score arraylist that holds the highscores score data
	 * @return returns the score arraylist
	 */

	public ArrayList<String> getScore() {
		return score;
	}
	
	/**
	 * Getter for name arraylist that holds the highscores player name data
	 * @return returns the name arraylist
	 */

	public ArrayList<String> getName() {
		return name;
	}

	/**
	 * Getter for date arraylist that holds the highscores date data
	 * @return returns the date arraylist
	 */
	
	public ArrayList<String> getDate() {
		return date;
	}
	
	/**
	 * Creates database connection by using jdbc driver
	 * @return returns the connection {@link Connection}
	 * @throws Exception catches the Exception e and posts it in console
	 */

	public Connection getConnection() throws Exception {

		try {
			String driver = "com.mysql.jdbc.Driver";
			// database osoite
			String connectionUrl = "jdbc:mysql://localhost:2280/highscores";
			String username = "kariants";
			String password = "pacman2017";
			Class.forName(driver);

			// luodaan yhteys
			Connection conn = DriverManager.getConnection(connectionUrl, username, password);
			System.out.println("Yhdisti");
			// palautetaan yhteys
			return conn;

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

		return null;

	}

	/**
	 *  This method is used to post player data to the highscore database
	 * @param score int value for players score
	 * @param playername String for players name
	 * @param databasename String for databases name
	 * @return returns true if post to database is successful
	 */
	public boolean post(int score, String playername, String databasename) {
		boolean sucess = false;
		Connection conn = null;
		try {
			conn = getConnection();

			java.sql.PreparedStatement query = conn
					.prepareStatement("INSERT INTO " + databasename + " (score, playername, submission_date) VALUES('"
							+ score + "', '" + playername + "', '" + currentDate() + "')");
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		try {
			conn.close();
			sucess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sucess;
	}
	
	/**
	 * This method selects the data from the database and sorts it by score
	 * highscore data is then added to score-, name- and date arraylists
	 * @param databasename selects the database to choose data from
	 */

	public void selectFromDatabase(String databasename) {
		Connection conn = null;
		try {
			conn = getConnection();

			java.sql.Statement select = conn.createStatement();
			String sql = "SELECT score, playername, submission_date FROM " + databasename + " ORDER BY score DESC";
			ResultSet resultset = select.executeQuery(sql);

			while (resultset.next()) {

				score.add(resultset.getInt("score") + "");
				name.add(resultset.getString("playername"));
				date.add(resultset.getString("submission_date"));

				// Display values
				System.out.print("Score: " + score);
				System.out.print(", Name: " + name);
				System.out.print(", Date: " + date);
				System.out.println(" ");
			}

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

	/**
	 * This method creates String of todays date
	 * @return returns todays date in year / month / date format
	 */
	
	public String currentDate() {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentDate = time.format(date);
		return currentDate;
	}

	/**
	 * This method is only used in JUnit tests
	 * Method clearTable completely clears the database table
	 */
	public void clearTable() {
		Connection conn = null;
		try {
			conn = getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			java.sql.PreparedStatement query = conn.prepareStatement("TRUNCATE TABLE scoreTest");
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
