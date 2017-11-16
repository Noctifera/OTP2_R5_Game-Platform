package hibernate;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import canvas.TextNode;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

/**
 * Used to save and get data from our mysql database
 * @author kari-antti
 * @version 1.0
 **/

public class DataBaseConnection {
	private static List<HighScores> scores = null;
	private static List<MapsTable> mapList = null;
	private static MapsTable usedMap = null;
	/**
	 * This method creates String of todays date
	 * @return returns todays date in year / month / date format
	 */
	
	private static String currentDate() {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentDate = time.format(date);
		return currentDate;
	}
	
	private static Session OpenConnectionToDataBase() {
		SessionFactory sessFac = null;

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		System.out.println("Configuration tiedosto ladattu");

		try {
			sessFac = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("istonto virhe");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}

		Session sess = sessFac.openSession();
		return sess;
	}
	
	/**
	 * This method selects the data from the database and sorts it by score
	 * highscore data is then added to score-, name- and date arraylists
	 * @param databasename selects the database to choose data from
	 */

	public static void getAllHighScoresFromDataBase() {
		Session conn = null;
		try {
			conn = OpenConnectionToDataBase();
		
			scores = conn.createQuery("from HighScores").list();
			for(HighScores h: scores) {
				System.out.println(h);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}finally {
			conn.close();
		}
	}
	public static boolean post(int score, String playername) {
		boolean sucess = false;
		Session conn = null;
		try {
			conn = OpenConnectionToDataBase();
			Transaction tran = conn.beginTransaction();

			HighScores hs = new HighScores(currentDate(),score,playername, usedMap);
			conn.saveOrUpdate(hs);
			tran.commit();
			sucess = true;
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}finally {
			conn.close();
		}
		return sucess;
	}
	
	public static void getAllMapsFromDataBase() {
		Session sess = OpenConnectionToDataBase();
		Transaction transaktio = null;

		try {
			mapList = sess.createQuery("from MapsTable").list();
			for (MapsTable m : mapList) {
				System.out.println(m);
			}

		} catch (Exception e) {
			System.out.println("transaktio virhe");
			if (transaktio != null)
				transaktio.rollback();
			throw e;
		} finally {
			sess.close();
		}
	}
	
	public static String[] allMapNames() {
		String[] names = new String[mapList.size()];
		for (int i = 0; i < mapList.size(); i++) {
			names[i] = mapList.get(i).getMapName();
		}
		return names;
	}
	
	public static HashMap<Point, String> readOneMap(String mapName) {
		for (MapsTable m : mapList) {
			if (m.getMapName().equals(mapName)) {
				usedMap = m;
				return m.getMapData();
			}
		}
		return null;
	}
	public static List<String> getMapNames(){
		ArrayList<String> list = new ArrayList<>();
		for(MapsTable mt: mapList) {
			list.add(mt.getMapName());
		}
		return list;
	}

	public static List<HighScores> getScores() {
		return scores;
	}

	public static List<MapsTable> getMapList() {
		return mapList;
	}

}
