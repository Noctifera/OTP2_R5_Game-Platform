package modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HighScore {

	/*
	 	java.util.Date date = new java.util.Date();

		java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("yyyy-MM-dd");

		String currentTime = time.format(date);
	*/

	private ArrayList<String> score = new ArrayList<>();
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<String> date = new ArrayList<>(); 
	
	public ArrayList<String> getScore() {
		return score;
	}

	public ArrayList<String> getName() {
		return name;
	}

	public ArrayList<String> getDate() {
		return date;
	}

	
	public Connection getConnection() throws Exception {

		try{
		  String driver = "com.mysql.jdbc.Driver";
		  //database osoite
		  String connectionUrl = "jdbc:mysql://localhost:2280/highscores";
		  String username = "kariants";
		  String password = "pacman2017";
		  Class.forName(driver);

		  //luodaan yhteys
		  Connection conn = DriverManager.getConnection(connectionUrl, username, password);
		  System.out.println("Yhdisti");
		  //palautetaan yhteys
		  return conn;

		} catch(Exception e){
		System.out.println("Error: "+ e);
		}

		return null;

		}

	public void post(int score, String playername, String date){
		Connection conn = null;
		try {
		   conn = getConnection();

		  java.sql.PreparedStatement query = conn.prepareStatement("INSERT INTO pacmanHighscore (score, playername, submission_date) VALUES('"+ score + "', '"+ playername +"', '"+date+"')");
		  query.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error: "+ e);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectFromDatabase() {
		Connection conn = null;
				try {
			conn = getConnection();

			java.sql.Statement select = conn.createStatement();
			String sql = "SELECT score, playername, submission_date FROM pacmanHighscore ORDER BY score DESC";
			ResultSet resultset = select.executeQuery(sql);

			while(resultset.next()){
		         
		         score.add(resultset.getInt("score")+ "");
		         name.add(resultset.getString("playername"));
		         date.add(resultset.getString("submission_date"));

		         //Display values
		         System.out.print("Score: " + score);
		         System.out.print(", Name: " + name);
		         System.out.print(", Date: " + date);
		         System.out.println(" ");
		      }


		}catch(Exception e){
			System.out.println("Error: "+ e);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
