package modal;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public interface Player_IF {
	public Point getPos(); // paluttaa pleyer:in posen;

	public void setPos(Point pos); // asetetaan playerin pose;

	public ArrayList<Point> move(KeyCode event); // "WASD" napeilla liikuminen palauttaa arrayListin ruudist johon voidaan liikua siihen suuntaan

	public void score(Point pos); // katotaan mitä pisteessä on ja paljonko pisteitä siitä saadaan palautetaan yhteis arvo

	public void getEaten(); // menetetään elämiä kun haamu syö

	public int getLife(); // palautetaan jäljellä olevia elämiä



}
