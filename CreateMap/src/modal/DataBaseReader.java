package modal;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class DataBaseReader {
	
	private List<MapsTable> mapList = null;
	
	private Map map;
	private Session sess;
	private Draw draw;

	public DataBaseReader(Map map,Draw draw) {
		this.map = map;
		this.draw = draw;
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

		sess = sessFac.openSession();
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
			sess = OpenConnectionToDataBase();
			
		 
		Transaction transaktio = null;

		try {
			mapList = sess.createQuery("from MapsTable").list();
			
		} catch (Exception e) {
			System.out.println("transaktio virhe");
			if (transaktio != null)
				transaktio.rollback();
			throw e;
		} finally {
			sess.close();
		}
	}

	public List<String> allMapNames() {
		List<String> names = new ArrayList<>();
		for(MapsTable mt : mapList) {
			names.add(mt.getMapName());
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

	public boolean SaveMapToDataBase(String fileName){
		 System.out.println(map.getMap().toString());

		if (doesNotContain(fileName)) {
				sess = OpenConnectionToDataBase();

			Transaction transaktio = null;

			try {
				transaktio = sess.beginTransaction();
				MapsTable hib = new MapsTable(fileName, map.getMap(),draw.getImage());

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
