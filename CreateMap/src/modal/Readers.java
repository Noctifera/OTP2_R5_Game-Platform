package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Readers {
	private final File folder = new File("C:\\Users\\marku\\git\\Game-Platform\\PacMan\\Maps");
	// File folder = new File("/Users/Hanne/git/Game-Platform/PacMan/Maps");
	private List<MapsTable> mapList = null;
	
	private Map map;

	public Readers(Map map) {
		this.map = map;
	}

	private Session OpenConnectionToDataBase() {
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

	public HashMap<Point, String> readMapFromFile(String tiedostonNimi) {
		File file = new File(folder + "//" + tiedostonNimi);
		HashMap<Point, String> tmp = new HashMap<>();
		try {

			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			tmp = (HashMap<Point, String>) dataIn.readObject();
			dataIn.close();
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private boolean doesNotContain(String mapName) {
		boolean notContain = true;
		for (MapsTable m : mapList) {
			if (mapName.equals(m.getMapName())) {
				notContain = false;
			}
		}
		return notContain;
	}

	public void getAllMapsFromDataBase() {
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

	public String[] allMapNames() {
		String[] names = new String[mapList.size()];
		for (int i = 0; i < mapList.size(); i++) {
			names[i] = mapList.get(i).getMapName();
		}
		return names;
	}

	public HashMap<Point, String> readOneMap(String mapName) {
		HashMap<Point, String> tmpMap = new HashMap<>();
		for (MapsTable m : mapList) {
			if (m.getMapName().equals(mapName)) {
				return m.getMapData();
			}
		}
		return tmpMap;
	}

	public String SaveMapToDataBase(String fileName) {
		 System.out.println(map.getMap().toString());
		// System.out.println(map.toString().length());
		if (doesNotContain(fileName)) {

			Session sess = OpenConnectionToDataBase();
			Transaction transaktio = null;

			try {
				transaktio = sess.beginTransaction();
				MapsTable hib = new MapsTable(fileName, map.getMap());

				sess.save(hib);
				transaktio.commit();
				return "map Saved";
			} catch (Exception e) {
				System.out.println("transaktio virhe");
				if (transaktio != null)
					transaktio.rollback();
				throw e;
			} finally {
				sess.close();
			}
		}else {
			return "Map name already exsits";
		}
	}

}
