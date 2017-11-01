package modal;

import java.awt.Point;
import java.util.HashMap;

import javax.persistence.*;

@Entity(name ="MapsTable")
@Table(name = "MapsTable")
public class MapsTable{
	
	@Id
	@Column(name = "mapName")
	private String mapName;
	
	@Column(name = "mapData", columnDefinition="blob")
	private HashMap<Point, String> mapData;
	

	public MapsTable(String mapName, HashMap<Point, String> mapData) {
		this.mapName = mapName;
		this.mapData = mapData;
	}


	public MapsTable() {
		
	}


	public String getMapName() {
		return mapName;
	}


	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	


	public HashMap<Point, String> getMapData() {
		return mapData;
	}


	public void setMapData(HashMap<Point, String> mapData) {
		this.mapData = mapData;
	}


	@Override
	public String toString() {
		return "Hibernate [mapId=" + ", mapName=" + mapName + ", mapData=" + mapData + "]";
	}
}