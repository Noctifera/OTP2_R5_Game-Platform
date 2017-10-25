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
	private List<MapsTable> mapList = null;
	private Map map;

	public Readers(Map map) {
		this.map = map;
	}

	public void readMapFromFile(String tiedostonNimi) {
		File file = new File(folder + "//" + tiedostonNimi);
		try {

			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			map.setMap((HashMap<Point, String>) dataIn.readObject());
			dataIn.close();

		} catch (Exception e) {
			e.printStackTrace();
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
		SessionFactory istuntotehdas = null;

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		System.out.println("Configuration tiedosto ladattu");

		try {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			// istuntotehdas = HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			System.out.println("istonto virhe");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}

		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;

		try {
			mapList = istunto.createQuery("from MapsTable").list();
			for (MapsTable m : mapList) {
				System.out.println(m);
			}

		} catch (Exception e) {
			System.out.println("transaktio virhe");
			if (transaktio != null)
				transaktio.rollback();
			throw e;
		} finally {
			istunto.close();
		}
	}

	public String[] allMapNames() {
		String[] names = new String[mapList.size()];
		for (int i = 0; i < mapList.size(); i++) {
			names[i] = mapList.get(i).getMapName();
		}
		return names;
	}

	public void readOneMap(String mapName) {
		HashMap<Point, String> tmpMap = new HashMap<>();
		for (MapsTable m : mapList) {
			if (m.getMapName().equals(mapName)) {
				String[] data = m.getMapData().split(", ");
				for (String s : data) {
					String[] sub = s.split("]=");
					Point p = null;
					for (int i = 0; i < sub.length; i++) {
						// System.out.println(sub[i]);

						if (i == 0) {
							String[] strings = sub[i].split("[^0-9]+");
							p = new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
							// System.out.println(p);
						}
						if (i == 1) {
							tmpMap.put(p, sub[i]);
							// System.out.println(p);
						}

					}
				}
			}
		}
		System.out.println(tmpMap);
		map.setMap(tmpMap);
	}

	public void SaveMapToDataBase(String fileName) {
		 System.out.println(map.getMap().toString());
		// System.out.println(map.toString().length());
		if (doesNotContain(fileName)) {
			SessionFactory istuntotehdas = null;

			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			System.out.println("Configuration tiedosto ladattu");

			try {
				istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
				// istuntotehdas = HibernateUtil.getSessionFactory();
			} catch (Exception e) {
				System.out.println("istonto virhe");
				StandardServiceRegistryBuilder.destroy(registry);
				e.printStackTrace();
			}

			Session istunto = istuntotehdas.openSession();
			Transaction transaktio = null;

			try {
				transaktio = istunto.beginTransaction();
				MapsTable hib = new MapsTable(fileName, map.getMap().toString());

				istunto.save(hib);
				transaktio.commit();

			} catch (Exception e) {
				System.out.println("transaktio virhe");
				if (transaktio != null)
					transaktio.rollback();
				throw e;
			} finally {
				istunto.close();
			}
			System.out.println("map Saved");
		}else {
			System.out.println("Map name already exsits");
		}
	}

}
