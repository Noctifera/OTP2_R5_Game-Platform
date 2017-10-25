package modal;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity(name ="MapsTable")
@Table(name = "MapsTable")
public class MapsTable{
	
	@Id
	@Column(name = "mapName")
	private String mapName;
	
	@Column(name = "mapData")
	@Type(type="text")
	private String mapData;
	

	public MapsTable(String mapName, String mapData) {
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


	public String getMapData() {
		return mapData;
	}


	public void setMapData(String mapData) {
		this.mapData = mapData;
	}


	@Override
	public String toString() {
		return "Hibernate [mapId=" + ", mapName=" + mapName + ", mapData=" + mapData + "]";
	}
}