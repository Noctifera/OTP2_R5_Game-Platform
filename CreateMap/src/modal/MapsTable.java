package modal;

import java.awt.Point;
import java.util.HashMap;

import javax.persistence.*;

import javafx.scene.image.Image;

@Entity(name ="MapsTable")
@Table(name = "MapsTable")
public class MapsTable{
	
	@Id
	@Column(name = "mapName")
	private String mapName;
	
	@Column(name = "mapData", columnDefinition="blob")
	private HashMap<Point, String> mapData;
	
	@Column(name = "mapImage", columnDefinition="blob")
	private Image mapImage;
	

	public MapsTable(String mapName, HashMap<Point, String> mapData,Image mapImage) {
		this.mapName = mapName;
		this.mapData = mapData;
		this.mapImage = mapImage;
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

	public Image getMapImage() {
		return mapImage;
	}

	public void setMapImage(Image mapImage) {
		this.mapImage = mapImage;
	}

	@Override
	public String toString() {
		return "Hibernate [mapId=" + ", mapName=" + mapName + ", mapData=" + mapData + "]";
	}
}