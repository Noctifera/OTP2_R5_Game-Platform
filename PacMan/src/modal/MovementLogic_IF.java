package modal;

import java.awt.Point;

public interface MovementLogic_IF {
	public Point yli(Point newpos); //mitä tapahtuu kun mennään alueen ulkopuolelle
	
	public Point up(Point point); //yhden ruudun liikuminen yl�s
	
	public Point down(Point point); //yhden ruudun liikumen alas
	
	
}
