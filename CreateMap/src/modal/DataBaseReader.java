package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DataBaseReader {
	
	private List<MapsTable> mapList = null;
	
	private Map map;

	public DataBaseReader(Map map) {
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

	public boolean SaveMapToDataBase(String fileName) {
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
				return true;
			} catch (Exception e) {
				System.out.println("transaktio virhe");
				if (transaktio != null)
					transaktio.rollback();
				throw e;
			} finally {
				sess.close();
			}
		}else {
			
			return false;
		}
	}

}
