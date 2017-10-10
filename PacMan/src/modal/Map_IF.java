package modal;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public interface Map_IF {

	public Point getPlayerSpawn(); //etsitään kartasta playerin spawn piste ja palautetaan se;

	public Point getGhostHouse(); //etsitään kartasta ghost house ja palautetaan sen piste;

	public void readMap(String tiedostonNimi); //luetaan kartta tiedostosta jonka nimi on tiedostonNimi

	public HashMap<Point, String> getMap();
	
	public ArrayList<Point> getWalls();
	
	public ArrayList<Point> freeSpaces();
	
	public void setDots();
	
	public ArrayList<Point> getDots();
	
	public void setLargeDots();
	
	public ArrayList<Point> getLargeDots();
	
	
	


}
