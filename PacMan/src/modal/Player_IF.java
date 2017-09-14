package modal;

import java.awt.Point;

import javafx.scene.input.KeyEvent;

public interface Player_IF {
	public Point getPos(); // paluttaa pleyer:in posen;

	public void setPos(Point pos); // asetetaan playerin pose;

	public void move(KeyEvent event); // "WASD" napeilla liikuminen ruudun verran

	public void score(Point pos); // katotaan mitä pisteessä on ja paljonko pisteitä siitä saadaan palautetaan yhteis arvo

	public void getEaten(); // menetetään elämiä kun haamu syö

	public int getLife(); // palautetaan jäljellä olevia elämiä



}
