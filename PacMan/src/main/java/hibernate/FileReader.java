package hibernate;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class FileReader {

	private static final File folder = new File("Maps");
	private static final File picture = new File("Pictures");

	private static List<MapsTable> mapList = null;

	public static void getAllMapsFromFile() {
		mapList = new ArrayList<>();
		File[] files = folder.listFiles();
		for (File f : files) {
			mapList.add(new MapsTable(f.getName(), readMapFromFile(f), readImageFromFile(f.getName().replaceAll(".txt", ""))));
		}
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

	public static HashMap<Point, String> getMap(String mapName) {
		for (MapsTable m : mapList) {
			if (m.getMapName().equals(mapName)) {
				System.out.println(m.getMapData());
				return m.getMapData();
			}
		}
		return null;

	}

	private static byte[] readImageFromFile(String mapName) {
		System.out.println(mapName);
		File f = new File(picture + "//" + mapName+".PNG");
		
		Image image = new  Image(f.toURI().toString());
		
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
}
