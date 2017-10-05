package modal;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public interface Ghost_IF {
	
	public ArrayList<Point> insPath(); // palauttaa polun jota liikutaan
	
	public Point getPos(); // palauttaa haamun sen hetkisen sijainnin
	
	public Color getColor(); // palautetaan javafx v‰ri haamun nimen perusteella
	
	public String getGhost(); // palauttaa hamun nimen
	
	public void setPos(Point pos); // s‰ikeell‰ p‰ivitet‰‰n haamun sijaintia
	
	public Point ghostHouse(); //palautetaan Ghosthousin sijainti;
}
