package hibernate_PacMan;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class FileReader {

	private static final File folder = new File("Maps");
	private static final File scoreFolder = new File(folder + "//" + "Scores.dat");

	private static List<HighScores> scores = null;
	private static List<MapsTable> mapList = null;
	private static MapsTable usedMap = null;

	public static void getAllMapsFromFile() {
		mapList = new ArrayList<>();
		File[] files = folder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.contains(".txt");
			}
		});
		for (File f : files) {
			mapList.add(new MapsTable(f.getName(), readMapFromFile(f), readImageFromFile(f.getName().replaceAll(".txt", ""))));
		}
	}

	private static String currentDate() {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentDate = time.format(date);
		return currentDate;
	}

	public static List<HighScores> scoreForMap(String mString) {
		List<HighScores> list = new ArrayList<>();
		for (HighScores hs : scores) {
			if (hs.getMapName().getMapName().equals(mString)) {
				list.add(hs);
			}
		}

		return list;
	}

	public static boolean post(int score, String playername) {

		boolean sucess = false;
		scores.add(new HighScores(currentDate(), score, playername, usedMap));
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(scoreFolder));
			oos.writeObject(scores);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sucess = true;
		return sucess;
	}

	private static void post(int score, String playername, MapsTable mt) {
		List<HighScores> mtlist = new ArrayList<>();
		mtlist.add(new HighScores(currentDate(), score, playername, mt));
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(scoreFolder));
			oos.writeObject(mtlist);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getAllHighScoresFromFile() {
		List<HighScores> highScores = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(scoreFolder));
			highScores = (List<HighScores>) ois.readObject();
			System.out.println(highScores);
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (mapList.size() > 0) {
				post(10, "test", mapList.get(0));
			}

			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (HighScores hs : highScores) {
			System.out.println(hs.getPlayername());
			System.out.println(hs.getScore());
		}
		scores = highScores;
	}

	private static HashMap<Point, String> readMapFromFile(File fileName) {

		HashMap<Point, String> tmp = new HashMap<>();
		try {

			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			tmp = (HashMap<Point, String>) dataIn.readObject();
			dataIn.close();
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static HashMap<Point, String> setFirstMap() {
		try {
			return getMap(mapList.get(0).getMapName());
		} catch (IndexOutOfBoundsException e) {
			
		}
		return null;
	}

	public static HashMap<Point, String> getMap(String mapName) {
		for (MapsTable m : mapList) {
			if (m.getMapName().equals(mapName)) {
				System.out.println(m.getMapData());
				usedMap = m;
				return m.getMapData();
			}
		}
		return null;

	}

	private static byte[] readImageFromFile(String mapName) {
		System.out.println(mapName);
		File f = new File(folder + "//" + mapName + ".PNG");

		Image image = new Image(f.toURI().toString());

		byte[] data = null;
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ImageIO.write(bImage, "PNG", baos);
			baos.flush();
			data = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	public static List<String> GetMapNamesFromFile() {
		List<String> list = new ArrayList<>();
		for (MapsTable mt : mapList) {
			list.add(mt.getMapName());
		}
		return list;
	}

	public static MapsTable getUsedMap() {
		return usedMap;
	}
}
