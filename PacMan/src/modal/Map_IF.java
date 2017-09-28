package modal;

import java.awt.Point;

public interface Map_IF {

	public Point getPlayerSpawn(); //etsitään kartasta playerin spawn piste ja palautetaan se;

	public Point getGhostHouse(); //etsitään kartasta ghost house ja palautetaan sen piste;

	public void readMap(String tiedostonNimi); //luetaan kartta tiedostosta jonka nimi on tiedostonNimi



}
