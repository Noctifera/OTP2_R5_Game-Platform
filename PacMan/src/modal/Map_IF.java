package modal;

import java.awt.Point;
import java.io.File;
import java.util.HashMap;

public interface Map_IF {
	public HashMap<Point, String> getMap(); //palauttaa HashMap Kartan

	public Point getPlayerSpawn(); //etsitään kartasta playerin spawn piste ja palautetaan se;

	public Point getGhostHouse(); //etsitään kartasta ghost house ja palautetaan sen piste;

	public void readMap(String tiedostonNimi); //luetaan kartta tiedostosta jonka nimi on tiedostonNimi

	public String[] allFiles(); // haetaan kaikki tiedostot Maps kansiosta ja palautetaan pelkkä nimi



}
